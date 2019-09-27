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
import ru.ifmo.volunteer.model.Volunteer;
import ru.ifmo.volunteer.service.VolunteerService;

@RestController
@RequestMapping("/api/v1/volunteer")
public class VolunteerController {

  private final VolunteerService volunteerService;

  public VolunteerController(final VolunteerService volunteerService) {
    this.volunteerService = volunteerService;
  }

  @ApiOperation(
      value = "Добавляет волонтёра и возвращает его",
      produces = "application/json",
      response = Volunteer.class)
  @PostMapping
  public Volunteer create(@RequestBody final Volunteer volunteer) {
    return volunteerService.add(volunteer);
  }

  @ApiOperation(
      value = "Возвращает список всех волонтёров",
      produces = "application/json",
      response = Volunteer.class,
      responseContainer = "List")
  @GetMapping
  public List<Volunteer> read() {
    return volunteerService.findAll();
  }

  @ApiOperation(
      value = "Возвращает волонтёра по id",
      produces = "application/json",
      response = Volunteer.class)
  @GetMapping("{id}")
  public Volunteer findById(@PathVariable final Long id) {
    return volunteerService.findById(id);
  }

  @ApiOperation(
      value = "Изменяет поля волонтёра и возвращает измененного волонтёра",
      produces = "application/json",
      response = Volunteer.class)
  @PutMapping
  public Volunteer update(@RequestBody final Volunteer volunteerData) {
    return volunteerService.update(volunteerData);
  }

  @ApiOperation(value = "Удаляет волонтёра по id")
  @DeleteMapping
  public void delete(final Long id) {
    volunteerService.deleteById(id);
  }
}
