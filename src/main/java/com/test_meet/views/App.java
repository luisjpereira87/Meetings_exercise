package com.test_meet.views;

import com.test_meet.controllers.ReservationController;
import com.test_meet.dto.ReservationsBoardDTO;
import java.awt.EventQueue;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Vector;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class App {

  private JFrame frame;
  private JTable table;
  private ReservationsForm reservationsForm = new ReservationsForm();

  /** Launch the application. */
  public static void main(String[] args) {
    EventQueue.invokeLater(
        new Runnable() {
          public void run() {
            try {
              App window = new App();
              window.frame.setVisible(true);
            } catch (Exception e) {
              e.printStackTrace();
            }
          }
        });
  }

  /** Create the application. */
  public App() throws ParseException {
    initialize();
  }

  /** Initialize the contents of the frame. */
  private void initialize() {
    frame = new JFrame();
    frame.setBounds(100, 100, 729, 471);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
    frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));

    table = new JTable(this.buildTableModel());
    table.setBounds(30, 40, 200, 300); // frame.getContentPane().add(table, BorderLayout.CENTER);

    JScrollPane sp = new JScrollPane(table);

    JPanel jpanel = new JPanel();

    JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, jpanel, sp);
    jpanel.setLayout(new BoxLayout(jpanel, BoxLayout.X_AXIS));

    JButton btnNewReservation = new JButton("New Reservation");

    btnNewReservation.addActionListener(e -> reservationsForm.setVisible(true));

    jpanel.add(btnNewReservation);

    frame.getContentPane().add(splitPane);
    frame.setVisible(true);

    this.reservationsForm.addWindowListener(
        new java.awt.event.WindowAdapter() {
          @Override
          public void windowClosing(java.awt.event.WindowEvent windowEvent) {
            table.setModel(buildTableModel());
          }
        });
  }

  public DefaultTableModel buildTableModel() {

    Vector<String> columnNames = new Vector<String>();
    columnNames.add("Building");
    columnNames.add("Floor");
    columnNames.add("Meeting Room");
    columnNames.add("Start");
    columnNames.add("End");

    List<ReservationsBoardDTO> reservationsBoardDTOList = new ReservationController().getBoard();
    Vector<Vector<Object>> data = new Vector<Vector<Object>>();

    for (int i = 0; i < reservationsBoardDTOList.size(); i++) {

      String buildingName = reservationsBoardDTOList.get(i).getBuildingName();
      String floorName = reservationsBoardDTOList.get(i).getFloorName();
      String meetingRoomName = reservationsBoardDTOList.get(i).getMeetingRoomName();
      LocalDateTime start = reservationsBoardDTOList.get(i).getStart();
      LocalDateTime end = reservationsBoardDTOList.get(i).getEnd();

      Vector<Object> vector = new Vector<Object>();
      vector.add(buildingName);
      vector.add(floorName);
      vector.add(meetingRoomName);
      vector.add(start);
      vector.add(end);

      data.add(vector);
    }

    return new DefaultTableModel(data, columnNames);
  }
}
