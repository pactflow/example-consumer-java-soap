package io.pactflow.example.xml.consumer.todo;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlRootElement(name = "task")
@XmlAccessorType(XmlAccessType.FIELD)
public class Task {
  @XmlAttribute(name = "id")
  private int id;
  @XmlAttribute(name = "name")
  private String name;
  @XmlAttribute(name = "done")
  private Boolean done;
}
