package ru.ifmo.volunteer.service;

import java.util.List;
import org.springframework.stereotype.Service;
import ru.ifmo.volunteer.exception.AlreadyExistsException;
import ru.ifmo.volunteer.exception.ResourceNotFoundException;
import ru.ifmo.volunteer.model.Request;
import ru.ifmo.volunteer.repository.RequestRepository;

@Service
public class RequestService {
    private final RequestRepository requestRepository;

  public RequestService(RequestRepository requestRepository) {
    this.requestRepository = requestRepository;
  }

  public Boolean hasAdditionalInfo(Long userId, Long museum_id) {
    return requestRepository.hasAdditionalInfo(userId, museum_id).isPresent();
  }

  public Request findById(Long id) {
    return requestRepository
        .findById(id)
        .orElseThrow(
            () -> new ResourceNotFoundException(String.format("Заявка с id %d не найдено", id)));
  }

  public Request add(Request request) {
        requestRepository.findById(request.getId())
        .ifPresent(
            e -> {
              throw new AlreadyExistsException(
                  String.format("Event with %d id already exists", request.getId()));
            });
    return requestRepository.save(request);
  }

  public void apply(Long eventId, Long requestId) {
    requestRepository.apply(eventId, requestId);
  }

  public List<Request> findAll() {
    return requestRepository.findAll();
  }

  public List<Request> findAllByUserId(final Long id) {
    return requestRepository.findAllByUserId(id);
  }

  public void update(final Request request) {
    requestRepository.save(request);
  }
}
