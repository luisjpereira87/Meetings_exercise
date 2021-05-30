package com.test_meet.controllers;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.test_meet.models.Reservation;
import com.test_meet.repositories.ReservationRepository;
import java.time.LocalDateTime;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

public class ReservationControllerTest extends TestCase {

  @Rule public ExpectedException expectedEx = ExpectedException.none();

  ReservationRepository reservationRepository = new ReservationRepository().getInstance();

  public void setUp() throws Exception {
    super.setUp();
  }

  @Before
  public void init() {
    reservationRepository.create(
        new Reservation(1, 1, LocalDateTime.now(), LocalDateTime.now().plusMinutes(10l)));
    reservationRepository.create(
        new Reservation(
            2, 2, LocalDateTime.now().plusMinutes(20l), LocalDateTime.now().plusMinutes(30l)));
  }

  public void tearDown() throws Exception {}

  /**
   * Test if there is availability for reservation
   *
   * @throws Exception
   */
  @Test
  public void testCreateSuccess() throws Exception {
    try {
      new ReservationController()
          .create(
              new Reservation(
                  null,
                  1,
                  LocalDateTime.now().plusMinutes(11l),
                  LocalDateTime.now().plusMinutes(20l)));
    } catch (Exception e) {
      fail("Should not have thrown any exception");
    }
  }

  /**
   * Test if date validation exceptions are being thrown
   *
   * @throws Exception
   */
  @Test
  public void testCreateErrorPastDate() throws Exception {

    Exception exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              new ReservationController()
                  .create(
                      new Reservation(
                          null, 1, LocalDateTime.now().minusMinutes(10l), LocalDateTime.now()));
            });
    String expectedMessage = "The dates have to be bigger than the current date";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  public void testCreateErrorExistReservation() throws Exception {

    Exception exception =
        assertThrows(
            Exception.class,
            () -> {
              new ReservationController()
                  .create(
                      new Reservation(
                          null,
                          1,
                          LocalDateTime.now().minusMinutes(10l),
                          LocalDateTime.now().plusMinutes(10l)));
            });

    String expectedMessage = "There is no availability to create the reservation";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }
}
