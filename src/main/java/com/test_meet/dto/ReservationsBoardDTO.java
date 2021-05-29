package com.test_meet.dto;

import java.time.LocalDateTime;

public class ReservationsBoardDTO {
  private String buildingName;
  private String floorName;
  private String meetingRoomName;
  private LocalDateTime start;
  private LocalDateTime end;

  public ReservationsBoardDTO(
      String buildingName,
      String floorName,
      String meetingRoomName,
      LocalDateTime start,
      LocalDateTime end) {
    this.buildingName = buildingName;
    this.floorName = floorName;
    this.meetingRoomName = meetingRoomName;
    this.start = start;
    this.end = end;
  }

  public String getBuildingName() {
    return buildingName;
  }

  public String getFloorName() {
    return floorName;
  }

  public String getMeetingRoomName() {
    return meetingRoomName;
  }

  public LocalDateTime getStart() {
    return start;
  }

  public LocalDateTime getEnd() {
    return end;
  }

  public void setBuildingName(String buildingName) {
    this.buildingName = buildingName;
  }

  public void setFloorName(String floorName) {
    this.floorName = floorName;
  }

  public void setMeetingRoomName(String meetingRoomName) {
    this.meetingRoomName = meetingRoomName;
  }

  public void setStart(LocalDateTime start) {
    this.start = start;
  }

  public void setEnd(LocalDateTime end) {
    this.end = end;
  }
}
