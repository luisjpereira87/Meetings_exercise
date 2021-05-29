package com.test_meet.models;

public class Floor {
  private Integer id;
  private String name;
  private Integer buildingId;

  public Floor(Integer id, String name, Integer buildingId) {
    super();
    this.id = id;
    this.name = name;
    this.buildingId = buildingId;
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

  public Integer getBuildingId() {
    return buildingId;
  }

  public void setBuildingId(Integer buildingId) {
    this.buildingId = buildingId;
  }
}
