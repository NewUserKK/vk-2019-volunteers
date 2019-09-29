package ru.ifmo.volunteer.repository;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

  @Transactional
  @Modifying
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

  @Transactional
  @Modifying
  @Query(value = "UPDATE users SET password=crypt(:password, password) WHERE login = :login", nativeQuery = true)
  void register(@Param("login") String login, @Param("password") String password);

  @Query(
      value =
          "SELECT * FROM users WHERE id IN "
              + "(SELECT user_id from user_to_role WHERE role_id = :roleId)",
      nativeQuery = true)
  List<User> getUsersByRoleId(@Param("roleId") Long roleId);

  @Query(value = "SELECT rating from users WHERE id = :id", nativeQuery = true)
  Long getRating(@Param("id") Long id);

  @Query(
      value = "SELECT COUNT(*) FROM event_to_participant WHERE event_id = :eventId",
      nativeQuery = true)
  Long getCountByEventId(@Param("eventId") Long eventId);

  @Query(
      value =
          "SELECT COUNT(*) "
              + "FROM event_to_request "
              + "INNER JOIN request ON request_id == request.id "
              + "INNER JOIN users ON request.user_id = users.id "
              + "ORDER BY users.rating LIMIT :limit",
      nativeQuery = true)
  List<User> getTop(@Param("limit") int limit);
}
