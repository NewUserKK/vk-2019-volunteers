package ru.ifmo.volunteer.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import ru.ifmo.volunteer.exception.AlreadyExistsException;
import ru.ifmo.volunteer.exception.ResourceNotFoundException;
import ru.ifmo.volunteer.model.Event;
import ru.ifmo.volunteer.model.Volunteer;
import ru.ifmo.volunteer.repository.EventRepository;
import ru.ifmo.volunteer.repository.VolunteerRepository;

@Service
public class EventService {
  private final EventRepository eventRepository;
  private final VolunteerRepository volunteerRepository;

  public EventService(EventRepository eventRepository, VolunteerRepository volunteerRepository) {
    this.eventRepository = eventRepository;
    this.volunteerRepository = volunteerRepository;
  }

  public List<Event> read() {
    return eventRepository.findAll();
  }

  public void deleteById(Long id) {
    eventRepository.deleteById(id);
  }

  public Event findById(Long id) {
    return eventRepository
        .findById(id)
        .orElseThrow(
            () -> new ResourceNotFoundException(String.format("Событие с id %d не найдено", id)));
  }

  public Event add(Event event) {
    eventRepository
        .findById(event.getId())
        .ifPresent(
            e -> {
              throw new AlreadyExistsException(
                  String.format("Event with %d id already exists", event.getId()));
            });
    return eventRepository.save(event);
  }

  public Event update(Event event) {
    findById(event.getId());
    return eventRepository.save(event);
  }

  public List<Event> getActualForUser(Long id) {
    return eventRepository.findAllByUserId(id).stream()
        .filter(event -> !event.getFinished())
        .collect(Collectors.toList());
  }

  public void subscribe(Long userId, Long eventId) {
    eventRepository
        .findEventWithUserId(userId, eventId)
        .ifPresent(u -> {
          throw new AlreadyExistsException("Вы уже были подписаны на это событие");
        });
    eventRepository.subscribe(userId, eventId);
  }

  public void unsubscribe(Long userId, Long eventId) {
    eventRepository
        .findEventWithUserId(userId, eventId)
        .orElseThrow(() -> new ResourceNotFoundException("Вы не были подписаны на это событие"));
    eventRepository.unsubscribe(userId, eventId);
  }

  public List<Event> getHistoryForUser(Long id) {
    return eventRepository.findAllByUserId(id).stream()
        .filter(Event::getFinished)
        .collect(Collectors.toList());
  }

  public List<Event> getEventsByMuseum(Long id) {
    return eventRepository.findAllByMuseumId(id).stream()
        .filter(event -> !event.getFinished())
        .collect(Collectors.toList());
  }

  public Boolean isFavourite(Long eventId, Long userId) {
    return eventRepository.isFavourite(eventId, userId) != null;
  }

  public void addResponsible(Long eventId, Long userId) {
    eventRepository.addResponsible(eventId, userId);
  }

  public Long ratingRequired(Long id) {
    return eventRepository.ratingRequired(id);
  }
}
