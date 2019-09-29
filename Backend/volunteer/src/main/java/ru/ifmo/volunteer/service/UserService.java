package ru.ifmo.volunteer.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import ru.ifmo.volunteer.exception.AlreadyExistsException;
import ru.ifmo.volunteer.exception.AuthorizationException;
import ru.ifmo.volunteer.exception.ResourceNotFoundException;
import ru.ifmo.volunteer.model.Event;
import ru.ifmo.volunteer.model.User;
import ru.ifmo.volunteer.repository.EventRepository;
import ru.ifmo.volunteer.repository.UserRepository;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final EventRepository eventRepository;

  public UserService(final UserRepository userRepository, EventRepository eventRepository) {
    this.userRepository = userRepository;
    this.eventRepository = eventRepository;
  }

  public User findById(final Long id) {
    return userRepository
        .findById(id)
        .orElseThrow(
            () ->
                new ResourceNotFoundException(
                    String.format("Представитель с id %d не найден", id)));
  }

  public User add(User user) {
    if (userRepository.findById(user.getId()).isPresent()) {
      throw new AlreadyExistsException(
          String.format("User with %d id already exists", user.getId()));
    }
    if (user.getRating() == null) user.setRating(0L);
    return userRepository.save(user);
  }

  public User findByToken(String token) {
    Optional<User> user = userRepository.findByToken(token);
    if (user.isPresent()) {
      return user.get();
    } else {
      var newAccount = new User();
      newAccount.setVkToken(token);
      return add(newAccount);
    }
  }

  public User update(final User user) {
    findById(user.getId());
    return userRepository.save(user);
  }

  public User authorize(String login, String password) {
    return userRepository
        .findByLoginAndPassword(login, password)
        .orElseThrow(() -> new AuthorizationException("Неверный логин или пароль"));
  }

  public List<User> findAll() {
    return userRepository.findAll();
  }

  public void deleteById(final Long id) {
    userRepository.deleteById(id);
  }

  public void addToFriends(Long userId, Long friendId) {
    userRepository.addToFriends(userId, friendId);
  }

  public List<User> getParticipantsById(Long id) {
    return userRepository.getParticipantsById(id);
  }

  public List<User> getParticipantsFriendsById(Long eventId, Long userId) {
    return userRepository.getParticipantsFriendsById(eventId, userId);
  }

  public Boolean isBlocked(Long userId, Long museumId) {
    return userRepository.isBlocked(userId, museumId) != null;
  }

  public void register(String login, String password) {
    userRepository.register(login, password);
  }

  public List<User> getUsersByRoleId(Long roleId) {
    return userRepository.getUsersByRoleId(roleId);
  }

  public Long getRating(Long id) {
    return userRepository.getRating(id);
  }

  public List<User> getDistribution(Long eventId) {
    Long alreadyExists = userRepository.getCountByEventId(eventId);
    Event event =
        eventRepository
            .findById(eventId)
            .orElseThrow(() -> new ResourceNotFoundException("Нет такого события"));
    long need = event.getVolunteersRequired() - alreadyExists;
    if (need <= 0) return Collections.EMPTY_LIST;
    Long importance = event.getImportance();
    var percents = (importance + 1) * 20;
    List<User> all = userRepository.findAll();
    if (all.size() <= need) return all;
    var limit = Math.round(need / 100 * percents);
    need -= limit;
    List<User> list =
        all.stream().sorted(Comparator.comparing(User::getRating)).collect(Collectors.toList());
    List<User> top = list.subList(0, limit);
    List<User> left =
        new ArrayList<>() {
          {
            addAll(list.subList(limit, list.size()));
          }
        };

    for (int i = 0; i < need; ++i) {
      int number = new Random().nextInt(left.size());
      top.add(left.get(number));
      left.remove(number);
    }
    top.forEach(user -> eventRepository.addParticipant(eventId, user.getId()));
    return top;
  }

  public void updateRating(Long rating, Long id) {
    userRepository.updateRating(rating, id);
  }
}
