package ru.ifmo.volunteer.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.ifmo.volunteer.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  @Query(
      value = "SELECT * FROM users WHERE login=:login AND password=crypt(:password, password)",
      nativeQuery = true)
  Optional<User> findByLoginAndPassword(
      @Param("login") String login, @Param("password") String password);

  @Query(value = "SELECT * FROM users WHERE vk_token = :token", nativeQuery = true)
  Optional<User> findByToken(@Param("token") String token);

  @Query(value = "INSERT INTO user_to_friend VALUES(:userId, :friendId)", nativeQuery = true)
  void addToFriends(@Param("userId") Long userId, @Param("friendId") Long friendId);

  @Query(
      value =
          "SELECT * FROM users WHERE id IN "
              + "(SELECT user_id FROM participants WHERE event_id = :eventId)",
      nativeQuery = true)
  List<User> getParticipantsById(@Param("eventId") Long eventId);

  @Query(
      value =
          "SELECT * FROM users WHERE id IN "
              + "(SELECT user_id FROM participants WHERE event_id = :eventId) AND "
              + "id IN (SELECT friend_id FROM user_to_friend WHERE user_id = :userId)",
      nativeQuery = true)
  List<User> getParticipantsFriendsById(
      @Param("eventId") Long eventId, @Param("userId") Long userId);

  @Query(
      value = "SELECT is_blocked FROM blocked WHERE used_id = :userId AND museum_id = :museumId",
      nativeQuery = true)
  Boolean isBlocked(@Param("userId") Long userId, @Param("museumId") Long museumId);
}
