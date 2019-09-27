package ru.ifmo.volunteer.service;

import java.util.List;
import org.springframework.stereotype.Service;
import ru.ifmo.volunteer.exception.AlreadyExistsException;
import ru.ifmo.volunteer.exception.ResourceNotFoundException;
import ru.ifmo.volunteer.model.Museum;
import ru.ifmo.volunteer.repository.MuseumRepository;

@Service
public class MuseumService {

  private final MuseumRepository museumRepository;

  public MuseumService(MuseumRepository museumRepository) {
    this.museumRepository = museumRepository;
  }

  public List<Museum> read() {
    return museumRepository.findAll();
  }

  public void deleteById(Long id) {
    museumRepository.deleteById(id);
  }

  public Museum findById(Long id) {
    return museumRepository
        .findById(id)
        .orElseThrow(
            () -> new ResourceNotFoundException(String.format("Событие с id %d не найдено", id)));
  }

  public Museum add(Museum museum) {
    museumRepository
        .findById(museum.getId())
        .ifPresent(
            e -> {
              throw new AlreadyExistsException(
                  String.format("Museum with %d id already exists", museum.getId()));
            });
    museumRepository.save(museum);
    return museum;
  }

  public Museum update(Museum museum) {
    findById(museum.getId());
    return museumRepository.save(museum);
  }
}
