package com.test_meet.models;

public class MeetingRoom {
  private Integer id;
  private String name;
  private Integer maximumAllocation;
  private Boolean isMultimediaCapabilities;
  private Integer floorId;

  public MeetingRoom(
      Integer id,
      String name,
      Integer maximumAllocation,
      Boolean isMultimediaCapabilities,
      Integer floorId) {
    super();
    this.id = id;
    this.name = name;
    this.maximumAllocation = maximumAllocation;
    this.isMultimediaCapabilities = isMultimediaCapabilities;
    this.floorId = floorId;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getMaximumAllocation() {
    return maximumAllocation;
  }

  public void setMaximumAllocation(Integer maximumAllocation) {
    this.maximumAllocation = maximumAllocation;
  }

  public Boolean getIsMultimediaCapabilities() {
    return isMultimediaCapabilities;
  }

  public void setIsMultimediaCapabilities(Boolean isMultimediaCapabilities) {
    this.isMultimediaCapabilities = isMultimediaCapabilities;
  }

  public Integer getFloorId() {
    return floorId;
  }

  public void setFloorId(Integer floorId) {
    this.floorId = floorId;
  }
}
