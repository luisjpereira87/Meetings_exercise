package com.test_meet.repositories;

import com.test_meet.models.Reservation;
import java.util.ArrayList;
import java.util.List;

public class ReservationRepository implements Repository<Reservation> {
  private static ReservationRepository INSTANCE;

  private List<Reservation> reservationList = new ArrayList<Reservation>();

  public ReservationRepository() {}

  public static ReservationRepository getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new ReservationRepository();
    }

    return INSTANCE;
  }

  /**
   * Get Floor by id
   *
   * @param id
   * @return
   */
  public Reservation findById(Integer id) {

    return this.reservationList.stream().filter(val -> id == val.getId()).findAny().orElse(null);
  }

  /**
   * Get Floor list
   *
   * @return
   */
  public List<Reservation> findAll() {

    return this.reservationList;
  }

  /**
   * Create reservation
   *
   * @param reservation
   */
  public void create(Reservation reservation) {
    this.reservationList.add(reservation);
  }
}
