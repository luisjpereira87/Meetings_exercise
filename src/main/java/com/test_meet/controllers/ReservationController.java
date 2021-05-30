package com.test_meet.controllers;

import com.test_meet.dto.ReservationsBoardDTO;
import com.test_meet.models.Building;
import com.test_meet.models.Floor;
import com.test_meet.models.MeetingRoom;
import com.test_meet.models.Reservation;
import com.test_meet.repositories.BuildingRepository;
import com.test_meet.repositories.FloorRepository;
import com.test_meet.repositories.MeetingRoomRepository;
import com.test_meet.repositories.ReservationRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ReservationController {
  private static int CLEAN_TIME = 5;
  ReservationRepository reservationRepository = new ReservationRepository().getInstance();
  MeetingRoomRepository metingRoomRepository = new MeetingRoomRepository().getInstance();
  BuildingRepository buildingRepository = new BuildingRepository().getInstance();
  FloorRepository floorRepository = new FloorRepository().getInstance();

  /**
   * Create new reservation
   *
   * @param reservation
   * @throws Exception
   */
  public void create(Reservation reservation) throws Exception {

    // Check if reservation is bigger than the current date
    if (reservation.getStart().isBefore(LocalDateTime.now())
        && reservation.getEnd().isBefore(LocalDateTime.now())) {
      throw new IllegalArgumentException("The dates have to be bigger than the current date");
    }

    // Add clean time
    MeetingRoom meetingRoom = this.metingRoomRepository.findById(reservation.getMeetingRoomId());
    long cleanTime = meetingRoom.getMaximumAllocation() + CLEAN_TIME;
    reservation.setEnd(reservation.getEnd().plusMinutes(cleanTime));

    // Filter and Sorted list by start date
    List<Reservation> reservationList =
        this.reservationRepository.findAll().stream()
            .filter(val -> val.getMeetingRoomId().equals(reservation.getMeetingRoomId()))
            .sorted(Comparator.comparing(Reservation::getStart))
            .collect(Collectors.toList());

    Boolean isReservation = true;

    // Set id in reservation
    int listSize = reservationList.size();
    reservation.setId(listSize + 1);

    for (int i = 0; i < listSize; i++) {
      Reservation current = reservationList.get(i);
      Reservation next;
      isReservation = false;

      try {
        next = reservationList.get(i + 1);
      } catch (Exception e) {
        next = null;
      }

      // Check if is possible add reservation inside list
      if ((reservation.getEnd().isBefore(current.getStart()) && i == 0)
          || (reservation.getStart().isAfter(current.getEnd()) && i == (listSize - 1))
          || (next != null
              && reservation.getStart().isAfter(current.getEnd())
              && reservation.getEnd().isBefore(next.getStart()))) {
        isReservation = true;
        break;
      }
    }

    if (isReservation) {
      this.reservationRepository.create(reservation);
    } else {
      throw new Exception("There is no availability to create the reservation");
    }
  }

  /**
   * Get Reservations Board list
   *
   * @return
   */
  public List<ReservationsBoardDTO> getBoard() {
    List<ReservationsBoardDTO> reservationsBoardDTOList = new ArrayList<>();

    for (Reservation reservation : this.reservationRepository.findAll()) {
      MeetingRoom meetingRoom = this.metingRoomRepository.findById(reservation.getMeetingRoomId());
      Floor floor = this.floorRepository.findById(meetingRoom.getFloorId());
      Building building = this.buildingRepository.findById(floor.getBuildingId());

      reservationsBoardDTOList.add(
          new ReservationsBoardDTO(
              building.getName(),
              floor.getName(),
              meetingRoom.getName(),
              reservation.getStart(),
              reservation.getEnd()));
    }

    return reservationsBoardDTOList;
  }
}
