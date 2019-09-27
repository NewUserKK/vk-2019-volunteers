package ru.ifmo.volunteer.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long event_id;

    private Long museum_id;

    private LocalDate start_date;

    private LocalDate end_date;

    private Long volunteers_required;

    private Long volunteers_present;

    private String type;

    private String photo_link;

    private String title;

    private String description;

    private String link_to_event;

    public Long getEvent_id() {
        return event_id;
    }

    public void setEvent_id(Long event_id) {
        this.event_id = event_id;
    }

    public Long getMuseum_id() {
        return museum_id;
    }

    public void setMuseum_id(Long museum_id) {
        this.museum_id = museum_id;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public LocalDate getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
    }

    public Long getVolunteers_required() {
        return volunteers_required;
    }

    public void setVolunteers_required(Long volunteers_required) {
        this.volunteers_required = volunteers_required;
    }

    public Long getVolunteers_present() {
        return volunteers_present;
    }

    public void setVolunteers_present(Long volunteers_present) {
        this.volunteers_present = volunteers_present;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhoto_link() {
        return photo_link;
    }

    public void setPhoto_link(String photo_link) {
        this.photo_link = photo_link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink_to_event() {
        return link_to_event;
    }

    public void setLink_to_event(String link_to_event) {
        this.link_to_event = link_to_event;
    }
}
