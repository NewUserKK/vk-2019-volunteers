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

  @ApiOperation(value = "Добавить в друзья", produces = "application/json")
  @PostMapping("/addFriend")
  public void addToFriends(@RequestParam Long userId, @RequestParam Long friendId) {
    userService.addToFriends(userId, friendId);
  }

  @ApiOperation(
      value = "Получение списка друзей на мероприятии",
      produces = "application/json",
      response = User.class,
      responseContainer = "List")
  @GetMapping("/participatedFriend")
  public List<User> getParticipatedFriend(@RequestParam Long eventId, @RequestParam Long userId) {
    return userService.getParticipantsFriendsById(eventId, userId);
  }

  @ApiOperation(
      value = "Возвращает рейтинг юзера",
      produces = "application/json",
      response = Long.class
  )
  @GetMapping("{id}/rating")
  public Long getRating(@PathVariable Long id) {
    return userService.getRating(id);
  }

  @ApiOperation(
      value = "Создаёт распределение участников на событие",
      produces = "application/json",
      response = User.class,
      responseContainer = "List"
  )
  @GetMapping("{eventId}/distribution")
  public List<User> getDistribution(@PathVariable Long eventId) {
    return userService.getDistribution(eventId);
  }

  @ApiOperation(
      value = "Создает юзера и возвращает его",
      produces = "application/json",
      response = User.class)
  @PostMapping
  public User create(@RequestBody final User user) {
    return userService.add(user);
  }

  @ApiOperation(
      value = "Добавляет пароль к юзеру",
      produces = "application/json")
  @PutMapping("/register")
  public void register(@RequestBody UserCredentials userCredentials) {
    userService.register(userCredentials.getLogin(), userCredentials.getPassword());
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
      value = "Возвращает список всех юзеров",
      produces = "application/json",
      response = User.class,
      responseContainer = "List")
  @GetMapping
  public List<User> read() {
    return userService.findAll();
  }

  @ApiOperation(
      value = "Возвращает юзера по id",
      produces = "application/json",
      response = User.class)
  @GetMapping("{id}")
  public User findById(@PathVariable final Long id) {
    return userService.findById(id);
  }

  @ApiOperation(
      value = "Изменяет юзера и возвращает его",
      produces = "application/json",
      response = User.class)
  @PutMapping
  public User update(@RequestBody final User user) {
    return userService.update(user);
  }

  @ApiOperation(value = "Удаляет юзера по id")
  @DeleteMapping
  public void delete(final Long id) {
    userService.deleteById(id);
  }

  @ApiOperation(
      value = "Возвращает true, если пользователь заблокирован, false -- иначе",
      produces = "application/json",
      response = Boolean.class)
  @GetMapping("{id}/blocked")
  public Boolean isBlocked(@PathVariable Long userId, @RequestParam Long museumId) {
    return userService.isBlocked(userId, museumId);
  }

//  @ApiOperation(
//      value = "Забанить юзера",
//      produces = "application/json",
//  )
//  @GetMapping("{id}/block")
//  public void block(@PathVariable Long )

  @ApiOperation(
      value = "Возвращает список участников с некоторой ролью",
      produces = "application/json",
      response = User.class,
      responseContainer = "List"
  )
  @GetMapping("byRole/{roleId}")
  public List<User> getUsersByRoleId(@PathVariable Long roleId) {
    return userService.getUsersByRoleId(roleId);
  }

  @ApiOperation(
      value = "Возвращает список участников для события",
      produces = "application/json",
      response = User.class,
      responseContainer = "List")
  @GetMapping("{id}/participants")
  public List<User> getParticipantsById(@PathVariable Long id) {
    return userService.getParticipantsById(id);
  }
}
