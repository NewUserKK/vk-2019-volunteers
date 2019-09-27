package ru.ifmo.volunteer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ifmo.volunteer.model.Museum;

public interface MuseumRepository extends JpaRepository<Museum, Long> {}
