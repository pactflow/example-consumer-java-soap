package io.pactflow.example.xml.consumer.todo;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
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
