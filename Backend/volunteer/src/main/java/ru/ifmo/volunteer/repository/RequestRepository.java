package ru.ifmo.volunteer.repository;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.ifmo.volunteer.model.Request;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {

  @Query(
      value =
          "SELECT * FROM user_to_additional_information "
              + "WHERE user_id = :userId AND museum_id = :museumId",
      nativeQuery = true)
  Optional<Object> hasAdditionalInfo(
      @Param("userId") Long userId, @Param("museumId") Long museumId);

  @Transactional
  @Modifying
  @Query(
      value = "INSERT INTO event_to_request(event_id, request_id) VALUES(:eventId, :requestId)",
      nativeQuery = true)
  void apply(@Param("eventId") Long eventId, @Param("requestId") Long requestId);

  @Query(
      value =
          "select request.* from request join event on request.event_id = event.id where event.responsible=? and not finished and status = 0",
      nativeQuery = true)
  List<Request> findAllByUserId(Long id);

  @Query(
      value = "SELECT * FROM request WHERE event_id = :eventId AND user_id = :userId",
      nativeQuery = true)
  Boolean requestExists(@Param("usedId") Long userId, @Param("eventId") Long eventId);
}
