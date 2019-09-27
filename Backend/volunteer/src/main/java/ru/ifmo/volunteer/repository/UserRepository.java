package ru.ifmo.volunteer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ifmo.volunteer.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
