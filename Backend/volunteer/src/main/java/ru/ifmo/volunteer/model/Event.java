package ru.ifmo.volunteer.model;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Event {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long eventId;

  private Long museumId;

  private LocalDate startDate;

  private LocalDate endDate;

  private Long volunteersRequired;

  private Long volunteersPresent;

  private String type;

  private String photoLink;

  private String title;

  private String description;

  private String linkToEvent;

  public Long getEventId() {
    return eventId;
  }

  public void setEventId(Long eventId) {
    this.eventId = eventId;
  }

  public Long getMuseumId() {
    return museumId;
  }

  public void setMuseumId(Long museumId) {
    this.museumId = museumId;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  public Long getVolunteersRequired() {
    return volunteersRequired;
  }

  public void setVolunteersRequired(Long volunteersRequired) {
    this.volunteersRequired = volunteersRequired;
  }

  public Long getVolunteersPresent() {
    return volunteersPresent;
  }

  public void setVolunteersPresent(Long volunteersPresent) {
    this.volunteersPresent = volunteersPresent;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getPhotoLink() {
    return photoLink;
  }

  public void setPhotoLink(String photoLink) {
    this.photoLink = photoLink;
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

  public String getLinkToEvent() {
    return linkToEvent;
  }

  public void setLinkToEvent(String linkToEvent) {
    this.linkToEvent = linkToEvent;
  }
}
