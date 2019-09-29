package ru.ifmo.volunteer.repository;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ifmo.volunteer.model.Event;
import ru.ifmo.volunteer.model.Volunteer;

public interface EventRepository extends JpaRepository<Event, Long> {

  @Query(
      value =
          "SELECT * "
              + "FROM event "
              + "INNER JOIN starred_events "
              + "ON event.id = starred_events.event_id "
              + "WHERE starred_events.volunteer_id = :id",
      nativeQuery = true)
  List<Event> findAllByUserId(@Param("id") Long id);

  @Transactional
  @Modifying
  @Query(value = "INSERT INTO starred_events VALUES(:userId, :eventId)", nativeQuery = true)
  void subscribe(@Param("userId") Long userId, @Param("eventId") Long eventId);

  @Transactional
  @Modifying
  @Query(
      value = "DELETE FROM starred_events WHERE event_id = :eventId AND volunteer_id = :userId",
      nativeQuery = true)
  void unsubscribe(@Param("userId") Long userId, @Param("eventId") Long eventId);

  @Query(value = "SELECT * FROM event WHERE museum_id = :id", nativeQuery = true)
  List<Event> findAllByMuseumId(@Param("id") Long id);

  @Query(
      value =
          "SELECT event_id FROM starred_events "
              + "WHERE event_id = :eventId AND volunteer_id = :userId IS NOT NULL",
      nativeQuery = true)
  Long isFavourite(@Param("eventId") Long eventId, @Param("userId") Long userId);

  @Query(
      value =
          "SELECT volunteer_id FROM starred_events "
              + "WHERE volunteer_id = :userId AND event_id = :eventId",
      nativeQuery = true)
  Optional<Long> findEventWithUserId(
      @Param("userId") Long userId, @Param("eventId") Long eventId);

  @Transactional
  @Modifying
  @Query(
      value = "UPDATE event SET responsible = :userId WHERE id = :eventId",
      nativeQuery = true
  )
  void addResponsible(Long eventId, Long userId);

  @Query(value = "SELECT required_rating FROM event WHERE id = :id", nativeQuery = true)
  Long ratingRequired(@Param("id") Long id);

  @Transactional
  @Modifying
  @Query(value = "UPDATE event SET finished = true WHERE id = :id", nativeQuery = true)
  void finish(@Param("id") Long id);
}
