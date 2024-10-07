package io.pactflow.example.xml.consumer.todo;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlRootElement(name = "project")
@XmlAccessorType(XmlAccessType.FIELD)
public class Project {
  @XmlAttribute(name = "id")
  private int id;
  @XmlAttribute(name = "type")
  private String type;
  @XmlAttribute(name = "name")
  private String name;
  @XmlAttribute(name = "due")
  private String due;

  @XmlElement(name = "tasks")
  private Tasks tasks;
}
