package ru.ifmo.volunteer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ifmo.volunteer.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
