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
import ru.ifmo.volunteer.model.Museum;
import ru.ifmo.volunteer.service.MuseumService;

@RestController
@RequestMapping("api/v1/museum")
public class MuseumController {
  private final MuseumService museumService;

  public MuseumController(final MuseumService museumService) {
    this.museumService = museumService;
  }

  @ApiOperation(
      value = "Возвращает список избранных музеев",
      produces = "application/json",
      response = Museum.class,
      responseContainer = "List")
  @GetMapping("{id}/favourites")
  public List<Museum> getFavourites(@PathVariable final long id) {
    return museumService.getFavourites(id);
  }

  @ApiOperation(
      value = "Добавляет музей и возвращает его",
      produces = "application/json",
      response = Museum.class)
  @PostMapping
  public Museum create(@RequestBody final Museum museum) {
    return museumService.add(museum);
  }

  @ApiOperation(
      value = "Возвращает музей по id",
      produces = "application/json",
      response = Museum.class)
  @GetMapping("{id}")
  public Museum findMuseumById(@PathVariable final long id) {
    return museumService.findById(id);
  }

  @ApiOperation(
      value = "Изменяет поля музея и возвращает измененный музей",
      produces = "application/json",
      response = Museum.class)
  @PutMapping
  public Museum update(@RequestBody final Museum museum) {
    return museumService.update(museum);
  }

  @ApiOperation(
      value = "Возвращает список всех музеев",
      produces = "application/json",
      response = Museum.class,
      responseContainer = "List")
  @GetMapping
  public List<Museum> read() {
    return museumService.read();
  }

  @ApiOperation(
      value = "Удаляет музей по id",
      produces = "application/json",
      response = Museum.class)
  @DeleteMapping("{id}")
  public void deleteById(@PathVariable final long id) {
    museumService.deleteById(id);
  }
}
