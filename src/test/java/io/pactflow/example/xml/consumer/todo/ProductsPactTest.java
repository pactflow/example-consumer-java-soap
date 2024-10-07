package io.pactflow.example.xml.consumer.todo;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import au.com.dius.pact.core.model.annotations.Pact;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.xml.PactXmlBuilder;
import au.com.dius.pact.core.model.PactSpecVersion;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.consumer.MockServer;
import static au.com.dius.pact.consumer.dsl.Matchers.bool;
import static au.com.dius.pact.consumer.dsl.Matchers.integer;
import static au.com.dius.pact.consumer.dsl.Matchers.string;
import static au.com.dius.pact.consumer.dsl.Matchers.timestamp;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.not;

@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "pactflow-example-provider-java-soap")
public class ProductsPactTest {
  @Pact(consumer = "pactflow-example-consumer-java-soap")
  public RequestResponsePact projects(PactDslWithProvider builder) {
    return builder
            .given("i have a list of projects")
            .uponReceiving("a request for projects in XML")
              .path("/projects")
              .query("from=today")
              .headers(Map.of("Accept", "application/xml"))
            .willRespondWith()
              .headers(Map.of("Content-Type", "application/xml"))
              .status(200)
            .body(new PactXmlBuilder("projects", "http://some.namespace/and/more/stuff").build(root -> {
              root.setAttributes(Map.of("id", "1234"));
              root.eachLike("project", 1, Map.of("id", integer(), "type", "activity", "name", string("Project 1"), "due",
                  timestamp("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", "2016-02-11T09:46:56.023Z")), project -> {
                    project.appendElement("tasks", Collections.emptyMap(), task -> {
                      task.eachLike("task", 1, Map.of("id", integer(), "name", string("Task 1"), "done", bool(true)));
                    });
                  });
            }))
            .toPact();
  }

  @PactTestFor(pactMethod = "projects", pactVersion = PactSpecVersion.V3)
  @Test
  public void testGeneratesAListOfTODOsForTheMainScreen(MockServer mockServer) throws IOException {
    Projects projects = new Todo().setUrl(mockServer.getUrl()).getProjects();
    assertThat(projects.getId(), is("1234"));
    assertThat(projects.getProjects(), hasSize(1));
    projects.getProjects().forEach(project -> {
      assertThat(project.getId(), is(greaterThan(0)));
      assertThat(project.getType(), is("activity"));
      assertThat(project.getName(), is("Project 1"));
      assertThat(project.getDue(), not(emptyString()));
      assertThat(project.getTasks().getTasks(), hasSize(1));
    });
  }
}
