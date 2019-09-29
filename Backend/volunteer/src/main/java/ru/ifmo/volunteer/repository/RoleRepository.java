package ru.ifmo.volunteer.repository;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.ifmo.volunteer.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

  @Query(value = "select * from event_to_roles etr join event e on e.id = etr.event_id join role r on r.id = etr.role_id where e.id = ?1", nativeQuery = true)
  List<Role> findByEventId(Long id);

  @Transactional
  @Modifying
  @Query(value = "insert into event_to_roles(event_id, role_id) values (?1, ?2)", nativeQuery = true)
  void addToEvent(Long eventId, Long roleId);
}
