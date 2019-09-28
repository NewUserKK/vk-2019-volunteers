package ru.ifmo.volunteer.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.ifmo.volunteer.model.Event;

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

  @Query(value = "INSERT INTO starred_events VALUES(:userId, :eventId)", nativeQuery = true)
  void subscribe(@Param("userId") Long userId, @Param("eventId") Long eventId);

  @Query(
      value = "DELETE FROM starred_events WHERE event_id = :eventId AND volunteer_id = :userId",
      nativeQuery = true)
  void unsubscribe(@Param("userId") Long userId, @Param("eventId") Long eventId);
}
