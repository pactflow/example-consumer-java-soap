package io.pactflow.example.xml.consumer.todo;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
@XmlRootElement(name = "projects", namespace = "http://some.namespace/and/more/stuff")
@XmlAccessorType(XmlAccessType.FIELD)
public class Projects {
  @XmlElement(name = "project", type = Project.class)
  private List<Project> projects = new ArrayList<>();

  @XmlAttribute(name = "id")
  private String id;
}
