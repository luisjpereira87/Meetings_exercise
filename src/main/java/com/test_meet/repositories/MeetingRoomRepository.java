package com.test_meet.repositories;

import com.test_meet.models.MeetingRoom;
import java.util.ArrayList;
import java.util.List;

public class MeetingRoomRepository implements Repository<MeetingRoom> {
  private static MeetingRoomRepository INSTANCE;

  private List<MeetingRoom> meetingRoomList = new ArrayList<MeetingRoom>();

  public MeetingRoomRepository() {

    this.meetingRoomList.add(new MeetingRoom(1, "MeetingRoom 1", 20, true, 1));
    this.meetingRoomList.add(new MeetingRoom(2, "MeetingRoom 2", 10, false, 2));
    this.meetingRoomList.add(new MeetingRoom(3, "MeetingRoom 3", 15, true, 4));
    this.meetingRoomList.add(new MeetingRoom(4, "MeetingRoom 4", 10, false, 4));
  }

  public static MeetingRoomRepository getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new MeetingRoomRepository();
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
  public MeetingRoom findById(Integer id) {

    return this.meetingRoomList.stream().filter(val -> id == val.getId()).findAny().orElse(null);
  }

  /**
   * Get Floor list
   *
   * @return
   */
  @Override
  public List<MeetingRoom> findAll() {

    return this.meetingRoomList;
  }

  /**
   * Create new MeetingRoom
   *
   * @param value
   */
  @Override
  public void create(MeetingRoom value) {
    this.meetingRoomList.add(value);
  }

  /**
   * Get MeetingRoom list by FloorId
   *
   * @return
   */
  public List<MeetingRoom> findAllByFloorId(Integer floorId) {

    List<MeetingRoom> meetingRoomListAux = new ArrayList<MeetingRoom>();

    for (MeetingRoom meetingRoom : this.meetingRoomList) {

      if (meetingRoom.getFloorId() == floorId) {
        meetingRoomListAux.add(meetingRoom);
      }
    }

    return meetingRoomListAux;
  }
}
