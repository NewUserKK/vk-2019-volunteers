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

  public EventController(EventService eventService) {
    this.eventService = eventService;
  }

  @ApiOperation(value = "Добавить ответственного", produces = "application/json")
  @PutMapping("{id}/addResponsible")
  public void addResponsible(@PathVariable Long id, @RequestParam Long userId) {
    eventService.addResponsible(id, userId);
  }

  @ApiOperation(
      value = "Получить минимальный рейтинг для участников события",
      produces = "application/json",
      response = Long.class)
  @GetMapping("{id}/rating_required")
  public Long ratingRequired(@PathVariable Long id) {
    return eventService.ratingRequired(id);
  }

  @ApiOperation(
      value = "Получение списка событий по музею",
      produces = "application/json",
      response = Event.class,
      responseContainer = "List")
  @GetMapping("{id}/events")
  public List<Event> getEventsByMuseum(@PathVariable Long id) {
    return eventService.getEventsByMuseum(id);
  }

  @ApiOperation(value = "Подписаться на событие", produces = "application/json")
  @PostMapping("/subscribe")
  public void subscribe(@RequestParam Long userId, @RequestParam Long eventId) {
    eventService.subscribe(userId, eventId);
  }

  @ApiOperation(
      value = "Находится ли событие в избранном",
      produces = "application/json",
      response = Boolean.class)
  @GetMapping("{eventId}/isFavourite")
  public Boolean isFavourite(@PathVariable Long eventId, @RequestParam Long userId) {
    return eventService.isFavourite(eventId, userId);
  }

  @ApiOperation(value = "Отписаться от события", produces = "application/json")
  @DeleteMapping("/unsubscribe")
  public void unsubscribe(@RequestParam Long userId, @RequestParam Long eventId) {
    eventService.unsubscribe(userId, eventId);
  }

  @ApiOperation(
      value = "Добавляет событие и возвращает его",
      produces = "application/json",
      response = Event.class)
  @PostMapping
  public Event create(@RequestBody Event event) {
    return eventService.add(event);
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
      value = "Возвращает список событий, на которые подписан пользователь",
      produces = "application/json",
      response = Event.class,
      responseContainer = "List")
  @GetMapping("{id}/actual")
  public List<Event> getActualForUser(@PathVariable Long id) {
    return eventService.getActualForUser(id);
  }

  @ApiOperation(value = "Возвращает историю событий, в которых участвовал пользователь")
  @GetMapping("{id}/history")
  public List<Event> getHistoryForUser(@PathVariable Long id) {
    return eventService.getHistoryForUser(id);
  }

  @ApiOperation(
      value = "Изменяет поля события и возвращает измененного события",
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
