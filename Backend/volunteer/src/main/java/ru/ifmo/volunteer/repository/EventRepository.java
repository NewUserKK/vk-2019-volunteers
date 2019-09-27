package ru.ifmo.volunteer.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.ifmo.volunteer.model.Event;

public interface EventRepository extends JpaRepository<Event, Long> {}
