package ru.ifmo.volunteer.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import ru.ifmo.volunteer.exception.AlreadyExistsException;
import ru.ifmo.volunteer.exception.ResourceNotFoundException;
import ru.ifmo.volunteer.model.Event;
import ru.ifmo.volunteer.model.User;
import ru.ifmo.volunteer.repository.EventRepository;
import ru.ifmo.volunteer.repository.UserRepository;

@Service
public class EventService {
  private final EventRepository eventRepository;
  private final UserRepository userRepository;

  public EventService(final EventRepository eventRepository, final UserRepository userRepository) {
    this.eventRepository = eventRepository;
    this.userRepository = userRepository;
  }

  public List<Event> read() {
    return eventRepository.findAll();
  }

  public void deleteById(final long id) {
    eventRepository.deleteById(id);
  }

  public Event findById(final long id) {
    return eventRepository
        .findById(id)
        .orElseThrow(
            () -> new ResourceNotFoundException(String.format("Событие с id %d не найдено", id)));
  }

  public Event add(final Event event) {
    eventRepository
        .findById(event.getId())
        .ifPresent(
            e -> {
              throw new AlreadyExistsException(
                  String.format("Event with %d id already exists", event.getId()));
            });
    return eventRepository.save(event);
  }

  public Event update(final Event event) {
    findById(event.getId());
    return eventRepository.save(event);
  }

  public List<Event> getActualForUser(final long id) {
    return eventRepository.findAllByUserId(id).stream()
        .filter(event -> !event.getFinished())
        .collect(Collectors.toList());
  }

  public void subscribe(final long userId, final long eventId) {
    eventRepository
        .findEventWithUserId(userId, eventId)
        .ifPresent(
            u -> {
              throw new AlreadyExistsException("Вы уже были подписаны на это событие");
            });
    eventRepository.subscribe(userId, eventId);
  }

  public void unsubscribe(final long userId, final long eventId) {
    eventRepository
        .findEventWithUserId(userId, eventId)
        .orElseThrow(() -> new ResourceNotFoundException("Вы не были подписаны на это событие"));
    eventRepository.unsubscribe(userId, eventId);
  }

  public List<Event> getHistoryForUser(final long id) {
    return eventRepository.findAllByUserId(id).stream()
        .filter(Event::getFinished)
        .collect(Collectors.toList());
  }

  public List<Event> getEventsByMuseum(final long id) {
    return eventRepository.findAllByMuseumId(id).stream()
        .filter(event -> !event.getFinished())
        .collect(Collectors.toList());
  }

  public Boolean isFavourite(final long eventId, final long userId) {
    return eventRepository.isFavourite(eventId, userId) != null;
  }

  public void addResponsible(final long eventId, final long userId) {
    eventRepository.addResponsible(eventId, userId);
    eventRepository.addRole(userId, 22L, eventId);
  }

  public Long ratingRequired(final long id) {
    return eventRepository.ratingRequired(id);
  }

  public void finish(final long id) {
    List<User> users = userRepository.getParticipantsById(id);
    users.forEach(user -> userRepository.updateRating(user.getId(), user.getRating() + 15));
    eventRepository.finish(id);
  }

  public void addRole(final long userId, final long roleId, final long eventId) {
    eventRepository.addRole(userId, roleId, eventId);
  }

  public void addParticipant(final long eventId, final long userId) {
    eventRepository.addParticipant(eventId, userId);
    eventRepository.increment(eventId);
  }
}
