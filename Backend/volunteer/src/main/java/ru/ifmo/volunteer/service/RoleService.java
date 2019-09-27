package ru.ifmo.volunteer.service;

import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import ru.ifmo.volunteer.exception.ResourceNotFoundException;
import ru.ifmo.volunteer.model.Role;
import ru.ifmo.volunteer.repository.RoleRepository;

@Service
public class RoleService {

  private final RoleRepository roleRepository;

  public RoleService(final RoleRepository roleRepository) {
    this.roleRepository = roleRepository;
  }

  public Role addOrUpdate(final Role role) {
    roleRepository.save(role);
    return role;
  }

  public List<Role> findAll() {
    return roleRepository.findAll();
  }

  public Role findById(final Long id) {
    return roleRepository
        .findById(id)
        .orElseThrow(
            () -> new ResourceNotFoundException(String.format("Роль с id %d не найдена", id)));
  }

  public void deleteById(final Long id) {
    roleRepository.deleteById(id);
  }

  public Role update(final Role role) {
    findById(role.getId());
    return addOrUpdate(role);
  }
}
