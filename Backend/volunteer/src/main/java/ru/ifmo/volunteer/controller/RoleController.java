package ru.ifmo.volunteer.controller;

import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ifmo.volunteer.model.Role;
import ru.ifmo.volunteer.service.RoleService;

@RestController
@RequestMapping("/api/v1/role")
public class RoleController {

  private final RoleService roleService;

  public RoleController(final RoleService roleService) {
    this.roleService = roleService;
  }

  @ApiOperation(
      value = "Добавляет роль и возвращает её",
      produces = "application/json",
      response = Role.class)
  @PostMapping
  public Role create(@RequestBody final Role role) {
    roleService.addOrUpdate(role);
    return role;
  }

  @ApiOperation(
      value = "Возвращает список всех ролей",
      produces = "application/json",
      response = Role.class,
      responseContainer = "List")
  @GetMapping
  public List<Role> read() {
    return roleService.findAll();
  }

  @ApiOperation(
      value = "Изменяет роль и возвращает её",
      produces = "application/json",
      response = Role.class)
  @PutMapping
  public Role update(@RequestBody final Role roleData) {
    final var role = roleService.findById(roleData.getId());
    BeanUtils.copyProperties(roleData, role, Role.class);
    roleService.addOrUpdate(role);
    return role;
  }

  @ApiOperation(value = "Удаляет роль по id")
  @DeleteMapping
  public void delete(final Long id) {
    roleService.deleteById(id);
  }
}
