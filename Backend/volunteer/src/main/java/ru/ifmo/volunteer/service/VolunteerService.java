package ru.ifmo.volunteer.service;

import org.springframework.stereotype.Service;
import ru.ifmo.volunteer.model.Volunteer;
import ru.ifmo.volunteer.repository.VolunteerRepository;

import java.util.List;

@Service
public class VolunteerService {

    private final VolunteerRepository volunteerRepository;

    public VolunteerService(VolunteerRepository volunteerRepository) {
        this.volunteerRepository = volunteerRepository;
    }

    public List<Volunteer> findAll() {
        return volunteerRepository.findAll();
    }
}
