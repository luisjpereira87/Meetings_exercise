package com.test_meet.models;

import java.time.LocalDateTime;

public class Reservation {
  private Integer id;
  private Integer meetingRoomId;
  private LocalDateTime start;
  private LocalDateTime end;

  public Reservation(Integer id, Integer meetingRoomId, LocalDateTime start, LocalDateTime end) {
    super();
    this.id = id;
    this.meetingRoomId = meetingRoomId;
    this.start = start;
    this.end = end;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getMeetingRoomId() {
    return meetingRoomId;
  }

  public void setMeetingRoomId(Integer meetingRoomId) {
    this.meetingRoomId = meetingRoomId;
  }

  public LocalDateTime getStart() {
    return start;
  }

  public void setStart(LocalDateTime start) {
    this.start = start;
  }

  public LocalDateTime getEnd() {
    return end;
  }

  public void setEnd(LocalDateTime end) {
    this.end = end;
  }
}
