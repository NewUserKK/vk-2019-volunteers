package ru.ifmo.volunteer.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.ifmo.volunteer.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

  @Query(value = "SELECT * FROM task WHERE event_id = :id", nativeQuery = true)
  List<Task> findAllTasksByEventId(@Param("id") Long id);
}
