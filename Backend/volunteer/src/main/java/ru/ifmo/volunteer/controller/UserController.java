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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.ifmo.volunteer.domain.UserCredentials;
import ru.ifmo.volunteer.model.User;
import ru.ifmo.volunteer.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

  private final UserService userService;

  public UserController(final UserService userService) {
    this.userService = userService;
  }

  @ApiOperation(
      value = "Добавить в друзья",
      produces = "application/json"
  )
  @PostMapping("/addFriend")
  public void addToFriends(@RequestParam Long userId, @RequestParam Long friendId) {
    userService.addToFriends(userId, friendId);
  }

  @ApiOperation(
      value = "Создает представителя музея и возвращает его",
      produces = "application/json",
      response = User.class)
  @PostMapping
  public User create(@RequestBody final User user) {
    return userService.add(user);
  }

  @ApiOperation(
      value = "Авторизация через vk",
      produces = "application/json",
      response = User.class)
  @PostMapping("/authVk")
  public User authWithVk(@RequestParam String token) {
    return userService.findByToken(token);
  }

  @ApiOperation(
      value = "Авторизация через логин-пароль",
      produces = "application/json",
      response = User.class)
  @PostMapping("/auth")
  public User authorize(@RequestBody UserCredentials userCredentials) {
    return userService.authorize(userCredentials.getLogin(), userCredentials.getPassword());
  }

  @ApiOperation(
      value = "Возвращает список всех представителей музея",
      produces = "application/json",
      response = User.class,
      responseContainer = "List")
  @GetMapping
  public List<User> read() {
    return userService.findAll();
  }

  @ApiOperation(
      value = "Возвращает представителя музея по id",
      produces = "application/json",
      response = User.class)
  @GetMapping("{id}")
  public User findById(@PathVariable final Long id) {
    return userService.findById(id);
  }

  @ApiOperation(
      value = "Изменяет представителя музея и возвращает его",
      produces = "application/json",
      response = User.class)
  @PutMapping
  public User update(@RequestBody final User user) {
    return userService.update(user);
  }

  @ApiOperation(value = "Удаляет представителя по id")
  @DeleteMapping
  public void delete(final Long id) {
    userService.deleteById(id);
  }
}
