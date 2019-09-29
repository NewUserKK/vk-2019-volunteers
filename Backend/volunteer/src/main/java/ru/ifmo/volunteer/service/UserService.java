package ru.ifmo.volunteer.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import ru.ifmo.volunteer.exception.AlreadyExistsException;
import ru.ifmo.volunteer.exception.AuthorizationException;
import ru.ifmo.volunteer.exception.ResourceNotFoundException;
import ru.ifmo.volunteer.model.User;
import ru.ifmo.volunteer.repository.EventRepository;
import ru.ifmo.volunteer.repository.UserRepository;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final EventRepository eventRepository;

  public UserService(final UserRepository userRepository, final EventRepository eventRepository) {
    this.userRepository = userRepository;
    this.eventRepository = eventRepository;
  }

  public User findById(final long id) {
    return userRepository
        .findById(id)
        .orElseThrow(
            () ->
                new ResourceNotFoundException(
                    String.format("Представитель с id %d не найден", id)));
  }

  public User add(final User user) {
    if (userRepository.findById(user.getId()).isPresent()) {
      throw new AlreadyExistsException(
          String.format("User with %d id already exists", user.getId()));
    }
    if (user.getRating() == null) user.setRating(0L);
    return userRepository.save(user);
  }

  public User findByToken(final String token) {
    final var user = userRepository.findByToken(token);
    if (user.isPresent()) {
      return user.get();
    } else {
      final var newAccount = new User();
      newAccount.setVkToken(token);
      return add(newAccount);
    }
  }

  public User update(final User user) {
    findById(user.getId());
    return userRepository.save(user);
  }

  public User authorize(final String login, final String password) {
    return userRepository
        .findByLoginAndPassword(login, password)
        .orElseThrow(() -> new AuthorizationException("Неверный логин или пароль"));
  }

  public List<User> findAll() {
    return userRepository.findAll();
  }

  public void deleteById(final long id) {
    userRepository.deleteById(id);
  }

  public void addToFriends(final long userId, final long friendId) {
    userRepository.addToFriends(userId, friendId);
  }

  public List<User> getParticipantsById(final long id) {
    return userRepository.getParticipantsById(id);
  }

  public List<User> getParticipantsFriendsById(final long eventId, final long userId) {
    return userRepository.getParticipantsFriendsById(eventId, userId);
  }

  public Boolean isBlocked(final long userId, final long museumId) {
    return userRepository.isBlocked(userId, museumId) != null;
  }

  public void register(final String login, final String password) {
    userRepository.register(login, password);
  }

  public List<User> getUsersByRoleId(final long roleId) {
    return userRepository.getUsersByRoleId(roleId);
  }

  public Long getRating(final long id) {
    return userRepository.getRating(id);
  }

  public List<User> getDistribution(final long eventId) {
    final var alreadyExists = userRepository.getCountByEventId(eventId);
    final var event =
        eventRepository
            .findById(eventId)
            .orElseThrow(() -> new ResourceNotFoundException("Нет такого события"));
    var need = event.getVolunteersRequired() - alreadyExists;
    if (need <= 0) return Collections.emptyList();
    final var importance = event.getImportance();
    final var percents = (importance + 1) * 20;
    final var all = userRepository.findAll();
    if (all.size() <= need) return all;
    final var limit = Math.round(need / 100f * percents);
    need -= limit;
    final var list =
        all.stream().sorted(Comparator.comparing(User::getRating)).collect(Collectors.toList());
    final var top = list.subList(0, limit);
    final var left =
        new ArrayList<User>() {
          {
            addAll(list.subList(limit, list.size()));
          }
        };

    for (var i = 0; i < need; ++i) {
      int number = new Random().nextInt(left.size());
      top.add(left.get(number));
      left.remove(number);
    }
    top.forEach(
        user -> {
          eventRepository.addParticipant(eventId, user.getId());
          eventRepository.increment(eventId);
        });
    return top;
  }

  public void updateRating(final long rating, final long id) {
    userRepository.updateRating(rating, id);
  }
}
