package ru.ifmo.volunteer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ifmo.volunteer.model.Volunteer;
import ru.ifmo.volunteer.service.VolunteerService;

import java.util.List;

@RestController
@RequestMapping("/api/volunteer")
public class VolunteerController {

    private final VolunteerService volunteerService;

    public VolunteerController(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }

    @GetMapping
    public List<Volunteer> read() {
        return volunteerService.findAll();
    }

}
