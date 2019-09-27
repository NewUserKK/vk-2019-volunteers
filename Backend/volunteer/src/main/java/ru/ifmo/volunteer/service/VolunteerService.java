package ru.ifmo.volunteer.service;

import java.util.List;
import org.springframework.stereotype.Service;
import ru.ifmo.volunteer.exception.ResourceNotFoundException;
import ru.ifmo.volunteer.model.Volunteer;
import ru.ifmo.volunteer.repository.VolunteerRepository;

@Service
public class VolunteerService {

  private final VolunteerRepository volunteerRepository;

  public VolunteerService(final VolunteerRepository volunteerRepository) {
    this.volunteerRepository = volunteerRepository;
  }

  public List<Volunteer> findAll() {
    return volunteerRepository.findAll();
  }

  public Volunteer findById(final Long id) {
    return volunteerRepository
        .findById(id)
        .orElseThrow(
            () -> new ResourceNotFoundException(String.format("Волонтёр с id %d не найден", id)));
  }

  public void addOrUpdate(final Volunteer volunteer) {
    volunteerRepository.save(volunteer);
  }

  public void deleteById(final Long id) {
    volunteerRepository.deleteById(id);
  }
}
