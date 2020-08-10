package io.pactflow.example.xml.consumer.todo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
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
