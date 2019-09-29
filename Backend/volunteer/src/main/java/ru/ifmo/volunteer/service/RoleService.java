package ru.ifmo.volunteer.service;

import java.util.List;
import org.springframework.stereotype.Service;
import ru.ifmo.volunteer.exception.AlreadyExistsException;
import ru.ifmo.volunteer.exception.ResourceNotFoundException;
import ru.ifmo.volunteer.model.Role;
import ru.ifmo.volunteer.repository.RoleRepository;

@Service
public class RoleService {

  private final RoleRepository roleRepository;

  public RoleService(final RoleRepository roleRepository) {
    this.roleRepository = roleRepository;
  }

  public Role add(final Role role) {
    if (roleRepository.findById(role.getId()).isPresent()) {
      throw new AlreadyExistsException(
          String.format("Role with %d id already exists", role.getId()));
    }
    return roleRepository.save(role);
  }

  public List<Role> findAll() {
    return roleRepository.findAll();
  }

  public Role findById(final long id) {
    return roleRepository
        .findById(id)
        .orElseThrow(
            () -> new ResourceNotFoundException(String.format("Роль с id %d не найдена", id)));
  }

  public void deleteById(final long id) {
    roleRepository.deleteById(id);
  }

  public Role update(final Role role) {
    findById(role.getId());
    return roleRepository.save(role);
  }

  public List<Role> findByEventId(final long id) {
    return roleRepository.findByEventId(id);
  }

  public void addToEvent(final long eventId, final long roleId) {
    roleRepository.addToEvent(eventId, roleId);
  }
}
