package ru.ifmo.volunteer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ifmo.volunteer.model.Volunteer;

@Repository
public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {
}
