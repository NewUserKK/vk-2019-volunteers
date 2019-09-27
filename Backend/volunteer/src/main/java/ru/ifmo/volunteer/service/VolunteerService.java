package ru.ifmo.volunteer.service;

import java.util.List;
import org.springframework.beans.BeanUtils;
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

  public Volunteer addOrUpdate(final Volunteer volunteer) {
    volunteerRepository.save(volunteer);
    return volunteer;
  }

  public void deleteById(final Long id) {
    volunteerRepository.deleteById(id);
  }

  public Volunteer update(final Volunteer volunteerData) {
    final var volunteer = findById(volunteerData.getId());
    BeanUtils.copyProperties(volunteerData, volunteer, Volunteer.class);
    return addOrUpdate(volunteer);
  }
}
