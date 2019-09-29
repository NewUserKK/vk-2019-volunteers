package ru.ifmo.volunteer.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Request {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private long event_id;

  private long user_id;

  private String comment;

  private int status;

  private long role_id;

  private long start_date;

  private long end_date;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getEvent_id() {
    return event_id;
  }

  public void setEvent_id(long event_id) {
    this.event_id = event_id;
  }

  public long getUser_id() {
    return user_id;
  }

  public void setUser_id(long user_id) {
    this.user_id = user_id;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public long getRole_id() {
    return role_id;
  }

  public void setRole_id(long role_id) {
    this.role_id = role_id;
  }

  public long getStart_date() {
    return start_date;
  }

  public void setStart_date(long start_date) {
    this.start_date = start_date;
  }

  public long getEnd_date() {
    return end_date;
  }

  public void setEnd_date(long end_date) {
    this.end_date = end_date;
  }
}
