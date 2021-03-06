package ru.ifmo.volunteer.controller;

import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    return roleService.add(role);
  }

  @ApiOperation(value = "Добавляет к событию роль")
  @PostMapping("{eventId}/add/{roleId}")
  public void addToEvent(@PathVariable final long eventId, @PathVariable final long roleId) {
    roleService.addToEvent(eventId, roleId);
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
      value = "Возвращает список всех ролей по id события",
      produces = "application/json",
      response = Role.class,
      responseContainer = "List")
  @GetMapping("byEvent/{id}")
  public List<Role> findByEventId(@PathVariable final long id) {
    return roleService.findByEventId(id);
  }

  @ApiOperation(
      value = "Возвращает роль по id",
      produces = "application/json",
      response = Role.class)
  @GetMapping("{id}")
  public Role findById(@PathVariable final long id) {
    return roleService.findById(id);
  }

  @ApiOperation(
      value = "Изменяет роль и возвращает её",
      produces = "application/json",
      response = Role.class)
  @PutMapping
  public Role update(@RequestBody final Role roleData) {
    return roleService.update(roleData);
  }

  @ApiOperation(value = "Удаляет роль по id")
  @DeleteMapping("{id}")
  public void delete(@PathVariable final long id) {
    roleService.deleteById(id);
  }
}
