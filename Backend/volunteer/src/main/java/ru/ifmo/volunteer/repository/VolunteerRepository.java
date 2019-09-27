package ru.ifmo.volunteer.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.ifmo.volunteer.model.Volunteer;

@Repository
public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {
  @Query(
      value = "SELECT * FROM volunteer WHERE login=:login AND password=crypt(:password, password)",
      nativeQuery = true)
  Optional<Volunteer> findByLoginAndPassword(
      @Param("login") String login, @Param("password") String password);
}
