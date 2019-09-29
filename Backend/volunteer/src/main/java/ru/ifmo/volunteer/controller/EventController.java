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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.ifmo.volunteer.model.Event;
import ru.ifmo.volunteer.service.EventService;

@RestController
@RequestMapping("api/v1/event")
public class EventController {
  private final EventService eventService;

  public EventController(final EventService eventService) {
    this.eventService = eventService;
  }

  @ApiOperation(value = "Добавить ответственного", produces = "application/json")
  @PutMapping("{id}/addResponsible")
  public void addResponsible(@PathVariable final long id, @RequestParam final long userId) {
    eventService.addResponsible(id, userId);
  }

  @ApiOperation(
      value = "Получить минимальный рейтинг для участников события",
      produces = "application/json",
      response = Long.class)
  @GetMapping("{id}/rating_required")
  public Long ratingRequired(@PathVariable final long id) {
    return eventService.ratingRequired(id);
  }

  @ApiOperation(
      value = "Получение списка событий по музею",
      produces = "application/json",
      response = Event.class,
      responseContainer = "List")
  @GetMapping("{id}/events")
  public List<Event> getEventsByMuseum(@PathVariable final long id) {
    return eventService.getEventsByMuseum(id);
  }

  @ApiOperation(value = "Подписаться на событие", produces = "application/json")
  @PostMapping("/subscribe")
  public void subscribe(@RequestParam final long userId, @RequestParam final long eventId) {
    eventService.subscribe(userId, eventId);
  }

  @ApiOperation(
      value = "Находится ли событие в избранном",
      produces = "application/json",
      response = Boolean.class)
  @GetMapping("{eventId}/isFavourite")
  public Boolean isFavourite(@PathVariable final long eventId, @RequestParam final long userId) {
    return eventService.isFavourite(eventId, userId);
  }

  @ApiOperation(value = "Завершить событие", produces = "application/json")
  @PutMapping("{id}/finish")
  public void finish(@PathVariable final long id) {
    eventService.finish(id);
  }

  @ApiOperation(value = "Отписаться от события", produces = "application/json")
  @DeleteMapping("/unsubscribe")
  public void unsubscribe(@RequestParam final long userId, @RequestParam final long eventId) {
    eventService.unsubscribe(userId, eventId);
  }

  @ApiOperation(
      value = "Добавляет событие и возвращает его",
      produces = "application/json",
      response = Event.class)
  @PostMapping
  public Event create(@RequestBody final Event event) {
    return eventService.add(event);
  }

  @ApiOperation(
      value = "Возвращает событие по id",
      produces = "application/json",
      response = Event.class)
  @GetMapping("{id}")
  public Event findEventById(@PathVariable final long id) {
    return eventService.findById(id);
  }

  @ApiOperation(
      value = "Возвращает список событий, на которые подписан пользователь",
      produces = "application/json",
      response = Event.class,
      responseContainer = "List")
  @GetMapping("{id}/actual")
  public List<Event> getActualForUser(@PathVariable final long id) {
    return eventService.getActualForUser(id);
  }

  @ApiOperation(value = "Возвращает историю событий, в которых участвовал пользователь")
  @GetMapping("{id}/history")
  public List<Event> getHistoryForUser(@PathVariable final long id) {
    return eventService.getHistoryForUser(id);
  }

  @ApiOperation(
      value = "Изменяет поля события и возвращает измененного события",
      produces = "application/json",
      response = Event.class)
  @PutMapping
  public Event update(@RequestBody final Event event) {
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
  @DeleteMapping("{id}")
  public void deleteById(@PathVariable final long id) {
    eventService.deleteById(id);
  }
}
