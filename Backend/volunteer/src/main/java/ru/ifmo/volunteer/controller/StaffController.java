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
import ru.ifmo.volunteer.model.Staff;
import ru.ifmo.volunteer.service.StaffService;

@RestController
@RequestMapping("/api/v1/staff")
public class StaffController {

  private final StaffService staffService;

  public StaffController(final StaffService staffService) {
    this.staffService = staffService;
  }

  @ApiOperation(
      value = "Создает представителя музея и возвращает его",
      produces = "application/json",
      response = Staff.class)
  @PostMapping
  public Staff create(@RequestBody final Staff staff) {
    return staffService.add(staff);
  }

  @ApiOperation(
      value = "Возвращает список всех представителей музея",
      produces = "application/json",
      response = Staff.class,
      responseContainer = "List")
  @GetMapping
  public List<Staff> read() {
    return staffService.findAll();
  }

  @ApiOperation(
      value = "Возвращает представителя музея по id",
      produces = "application/json",
      response = Staff.class)
  @GetMapping("{id}")
  public Staff findById(@PathVariable final Long id) {
    return staffService.findById(id);
  }

  @ApiOperation(
      value = "Изменяет представителя музея и возвращает его",
      produces = "application/json",
      response = Staff.class)
  @PutMapping
  public Staff update(@RequestBody final Staff staff) {
    return staffService.update(staff);
  }

  @ApiOperation(value = "Удаляет представителя по id")
  @DeleteMapping
  public void delete(final Long id) {
    staffService.deleteById(id);
  }
}
