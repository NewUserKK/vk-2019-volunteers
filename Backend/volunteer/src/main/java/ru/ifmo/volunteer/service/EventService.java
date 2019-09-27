package ru.ifmo.volunteer.service;

import java.util.List;
import org.springframework.stereotype.Service;
import ru.ifmo.volunteer.exception.ResourceNotFoundException;
import ru.ifmo.volunteer.model.Event;
import ru.ifmo.volunteer.repository.EventRepository;

@Service
public class EventService {
  private final EventRepository eventRepository;

  public EventService(EventRepository eventRepository) {
    this.eventRepository = eventRepository;
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

  public Event addOrUpdate(Event event) {
    eventRepository.save(event);
    return event;
  }

  public Event update(Event event) {
    findById(event.getId());
    return addOrUpdate(event);
  }
}