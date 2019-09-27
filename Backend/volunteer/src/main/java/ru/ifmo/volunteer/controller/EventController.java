package ru.ifmo.volunteer.controller;

import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ifmo.volunteer.model.Event;
import ru.ifmo.volunteer.repository.EventRepository;
import ru.ifmo.volunteer.service.EventService;

@RestController
@RequestMapping("api/v1/event")
public class EventController {
  private final EventService eventService;

  public EventController(EventService eventService) {
    this.eventService = eventService;
  }

  @ApiOperation(
      value = "Добавляет событие и возвращает его",
      produces = "application/json",
      response = Event.class)
  @PostMapping
  public Event create(Event event) {
    return eventService.addOrUpdate(event);
  }

  @ApiOperation(
      value = "Возвращает событие по id",
      produces = "application/json",
      response = Event.class)
  @GetMapping("{id}")
  public Event findEventById(@PathVariable Long id) {
    return eventService.findById(id);
  }

  @ApiOperation(
      value = "Изменяет поля события и возвращает измененного событие",
      produces = "application/json",
      response = Event.class)
  @PutMapping
  public Event update(@RequestBody Event event) {
    return eventService.update(event);
  }

  @ApiOperation(
      value = "Возвращает список всех событий",
      produces = "application/json",
      response = Event.class,
      responseContainer = "List")
  @GetMapping
  public List<Event> read() {
    return eventService.read();
  }

  @ApiOperation(
      value = "Удаляет событие по id",
      produces = "application/json",
      response = Event.class)
  @DeleteMapping
  public void deleteById(Long id) {
    eventService.deleteById(id);
  }
}