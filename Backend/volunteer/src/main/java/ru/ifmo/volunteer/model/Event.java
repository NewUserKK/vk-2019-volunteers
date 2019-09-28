package ru.ifmo.volunteer.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Event {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "museum_id")
  private Museum museum;

  private Long startDate;

  private Long endDate;

  private Long volunteersRequired;

  private Long volunteersPresent;

  private String type;

  private String photoLink;

  private String title;

  private String description;

  private String linkToEvent;

  private Boolean finished;

  public Boolean getFinished() {
    return finished;
  }

  public void setFinished(Boolean finished) {
    this.finished = finished;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Museum getMuseum() {
    return museum;
  }

  public void setMuseum(Museum museum) {
    this.museum = museum;
  }

  public Long getStartDate() {
    return startDate;
  }

  public void setStartDate(Long startDate) {
    this.startDate = startDate;
  }

  public Long getEndDate() {
    return endDate;
  }

  public void setEndDate(Long endDate) {
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
