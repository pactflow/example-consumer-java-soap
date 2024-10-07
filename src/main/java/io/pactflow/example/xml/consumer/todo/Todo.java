package io.pactflow.example.xml.consumer.todo;

import org.apache.http.client.fluent.Request;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import java.io.IOException;

public class Todo {
  private String url;

  public Todo setUrl(String url) {
    this.url = url;
    return this;
  }

  public Projects getProjects() throws IOException {
    return (Projects) Request.Get(this.url + "/projects?from=today")
      .addHeader("Accept", "application/xml")
      .execute().handleResponse(httpResponse -> {
        try {
          JAXBContext jaxbContext = JAXBContext.newInstance(Projects.class);
          Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
          return jaxbUnmarshaller.unmarshal(httpResponse.getEntity().getContent());
        } catch (JAXBException e) {
          throw new IOException(e);
        }
      });
  }
}
