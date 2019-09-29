package ru.ifmo.volunteer.service;

import org.springframework.stereotype.Service;
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
}
