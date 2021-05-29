package com.test_meet.repositories;

import com.test_meet.models.Building;
import java.util.ArrayList;
import java.util.List;

public class BuildingRepository implements Repository<Building> {

  private static BuildingRepository INSTANCE;

  private List<Building> builndingList = new ArrayList<Building>();

  public BuildingRepository() {

    this.builndingList.add(new Building(1, "build 1"));
    this.builndingList.add(new Building(2, "build 2"));
    this.builndingList.add(new Building(3, "build 3"));
    this.builndingList.add(new Building(4, "build 4"));
  }

  public static BuildingRepository getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new BuildingRepository();
    }

    return INSTANCE;
  }

  /**
   * Get Building by id
   *
   * @param id
   * @return
   */
  @Override
  public Building findById(Integer id) {

    return this.builndingList.stream().filter(val -> id == val.getId()).findAny().orElse(null);
  }

  /**
   * Get Building list
   *
   * @return
   */
  @Override
  public List<Building> findAll() {

    return this.builndingList;
  }

  @Override
  public void create(Building value) {
    this.builndingList.add(value);
  }
}
