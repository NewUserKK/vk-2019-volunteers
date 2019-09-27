package ru.ifmo.volunteer.service;

import java.util.List;
import org.springframework.stereotype.Service;
import ru.ifmo.volunteer.exception.AlreadyExistsException;
import ru.ifmo.volunteer.exception.ResourceNotFoundException;
import ru.ifmo.volunteer.model.Staff;
import ru.ifmo.volunteer.repository.StaffRepository;

@Service
public class StaffService {

  private final StaffRepository staffRepository;

  public StaffService(final StaffRepository staffRepository) {
    this.staffRepository = staffRepository;
  }

  public Staff findById(final Long id) {
    return staffRepository
        .findById(id)
        .orElseThrow(
            () ->
                new ResourceNotFoundException(
                    String.format("Представитель с id %d не найден", id)));
  }

  public Staff add(Staff staff) {
    if (staffRepository.findById(staff.getId()).isPresent()) {
      throw new AlreadyExistsException(
          String.format("Staff with %d id already exists", staff.getId()));
    }
    staffRepository.save(staff);
    return staff;
  }

  public Staff update(final Staff staff) {
    findById(staff.getId());
    return staffRepository.save(staff);
  }

  public List<Staff> findAll() {
    return staffRepository.findAll();
  }

  public void deleteById(final Long id) {
    staffRepository.deleteById(id);
  }
}
