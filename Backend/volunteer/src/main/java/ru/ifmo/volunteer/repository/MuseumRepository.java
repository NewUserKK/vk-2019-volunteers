package ru.ifmo.volunteer.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.ifmo.volunteer.model.Museum;

public interface MuseumRepository extends JpaRepository<Museum, Long> {

  @Query(
      value = "SELECT * FROM museum WHERE id in "
          + "(SELECT museum_id FROM favourite_museums where volunteer_id = :id)",
      nativeQuery = true)
  List<Museum> getFavourites(@Param("id") Long id);
}
