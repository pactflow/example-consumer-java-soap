package io.pactflow.example.xml.consumer.todo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
@XmlRootElement(name = "tasks")
@XmlAccessorType(XmlAccessType.FIELD)
public class Tasks {
  @XmlElement(name = "task", type = Task.class)
  private List<Task> tasks = new ArrayList<>();
}
