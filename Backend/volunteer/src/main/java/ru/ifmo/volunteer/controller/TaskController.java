package ru.ifmo.volunteer.controller;

import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ifmo.volunteer.model.Task;
import ru.ifmo.volunteer.service.TaskService;

@RestController
@RequestMapping("api/v1/task")
public class TaskController {
  private final TaskService taskService;

  public TaskController(final TaskService taskService) {
    this.taskService = taskService;
  }

  @ApiOperation(
      value = "Получения списка всех заданий для события",
      produces = "application/json",
      response = Task.class,
      responseContainer = "List")
  @GetMapping("{id}/tasks")
  public List<Task> getAllTasksByEventId(@PathVariable final long id) {
    return taskService.getAllTasksByEventId(id);
  }
}
