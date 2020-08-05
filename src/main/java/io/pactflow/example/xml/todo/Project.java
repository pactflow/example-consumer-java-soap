package io.pactflow.example.xml.todo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
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
