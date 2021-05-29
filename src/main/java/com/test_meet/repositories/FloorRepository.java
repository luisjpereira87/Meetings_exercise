package com.test_meet.repositories;

import com.test_meet.models.Floor;
import java.util.ArrayList;
import java.util.List;

public class FloorRepository implements Repository<Floor> {
  private static FloorRepository INSTANCE;

  private List<Floor> floorList = new ArrayList<Floor>();

  public FloorRepository() {

    this.floorList.add(new Floor(1, "floor 1", 1));
    this.floorList.add(new Floor(2, "floor 2", 2));
    this.floorList.add(new Floor(3, "floor 3", 3));
    this.floorList.add(new Floor(4, "floor 4", 4));
  }

  public static FloorRepository getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new FloorRepository();
    }

    return INSTANCE;
  }

  /**
   * Get Floor by id
   *
   * @param id
   * @return
   */
  @Override
  public Floor findById(Integer id) {

    return this.floorList.stream().filter(val -> id == val.getId()).findAny().orElse(null);
  }

  /**
   * Get Floor list
   *
   * @return
   */
  @Override
  public List<Floor> findAll() {

    return this.floorList;
  }

  /**
   * Create new Floor
   *
   * @param value
   */
  @Override
  public void create(Floor value) {
    this.floorList.add(value);
  }

  /**
   * Get Floor list by BuildingId
   *
   * @return
   */
  public List<Floor> findAllByBuildingId(Integer buildingId) {

    List<Floor> floorListAux = new ArrayList<Floor>();

    for (Floor floor : this.floorList) {

      if (floor.getBuildingId() == buildingId) {
        floorListAux.add(floor);
      }
    }

    return floorListAux;
  }
}
