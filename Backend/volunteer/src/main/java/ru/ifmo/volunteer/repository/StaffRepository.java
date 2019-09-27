package ru.ifmo.volunteer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ifmo.volunteer.model.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {}
