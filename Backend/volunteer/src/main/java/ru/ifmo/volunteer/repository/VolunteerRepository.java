package ru.ifmo.volunteer.repository;

import java.util.List;
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

  @Query(
      value =
          "SELECT * FROM volunteer WHERE volunteer.id IN "
              + "(SELECT user_id FROM participants WHERE event_id = :event_id)",
      nativeQuery = true)
  List<Volunteer> getParticipantsById(@Param("eventId") Long eventId);
}
