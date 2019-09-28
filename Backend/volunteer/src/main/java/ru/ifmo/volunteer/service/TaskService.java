package ru.ifmo.volunteer.service;

import java.util.List;
import org.springframework.stereotype.Service;
import ru.ifmo.volunteer.model.Task;
import ru.ifmo.volunteer.repository.TaskRepository;

@Service
public class TaskService {
  private final TaskRepository taskRepository;

  public TaskService(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  public List<Task> getAllTasksByEventId(Long id) {
    return taskRepository.findAllTasksByEventId(id);
  }
}
