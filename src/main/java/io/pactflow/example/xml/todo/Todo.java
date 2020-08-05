package io.pactflow.example.xml.todo;

import org.apache.http.client.fluent.Request;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
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