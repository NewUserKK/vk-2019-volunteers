package ru.ifmo.volunteer.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.ifmo.volunteer.model.User;
import ru.ifmo.volunteer.model.Volunteer;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  @Query(
      value = "SELECT * FROM user WHERE login=:login AND password=crypt(:password, password)",
      nativeQuery = true)
  Optional<User> findByLoginAndPassword(
      @Param("login") String login, @Param("password") String password);

  @Query(
      value = "SELECT * FROM user WHERE vk_token = :token",
      nativeQuery = true
  )
  Optional<User> findByToken(@Param("token") String token);
}
