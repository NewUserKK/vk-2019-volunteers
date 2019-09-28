package ru.ifmo.volunteer.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import ru.ifmo.volunteer.exception.AlreadyExistsException;
import ru.ifmo.volunteer.exception.AuthorizationException;
import ru.ifmo.volunteer.exception.ResourceNotFoundException;
import ru.ifmo.volunteer.model.User;
import ru.ifmo.volunteer.repository.UserRepository;

@Service
public class UserService {

  private final UserRepository userRepository;

  public UserService(final UserRepository userRepository) {
    this.userRepository = userRepository;
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
    userRepository.save(user);
    return user;
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
}
