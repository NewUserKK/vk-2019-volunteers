package ru.ifmo.volunteer.controller;

import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.ifmo.volunteer.model.Request;
import ru.ifmo.volunteer.service.RequestService;

@RestController
@RequestMapping("api/v1/request")
public class RequestController {
  private final RequestService requestService;

  public RequestController(RequestService requestService) {
    this.requestService = requestService;
  }

  @ApiOperation(
      value = "Возвращает все заявки",
      produces = "application/json",
      response = Request.class,
      responseContainer = "List"
  )
  @GetMapping
  public List<Request> read() {
    return requestService.findAll();
  }

  @ApiOperation(
      value = "Возвращает все заявки по событию (по его представителю)",
      produces = "application/json",
      response = Request.class,
      responseContainer = "List"
  )
  @GetMapping("{id}")
  public List<Request> byUser(@PathVariable final Long id) {
    return requestService.findAllByUserId(id);
  }

  @ApiOperation(
      value = "Информация о том, заполнял ли пользователь форму для данного музея",
      produces = "application/json",
      response = Boolean.class
  )
  @GetMapping("{userId}/info")
  public Boolean hasAdditionalInfo(@PathVariable Long userId, @RequestParam Long museum_id) {
    return requestService.hasAdditionalInfo(userId, museum_id);
  }

  @ApiOperation(
      value = "Подать заявку на волонтёрство",
      produces = "application/json"
  )
  @PostMapping("{eventId}/apply")
  public void apply(@PathVariable Long eventId, @RequestParam Long requestId) {
    requestService.apply(eventId, requestId);
  }

  @ApiOperation(
      value = "Создать заяку на волонтёрство",
      produces = "application/json",
      response = Request.class
  )
  @PostMapping
  public Request add(@RequestBody Request request) {
    return requestService.add(request);
  }

}
