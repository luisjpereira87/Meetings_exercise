package com.test_meet.views;

import com.test_meet.controllers.ReservationController;
import com.test_meet.dto.ComboItemDTO;
import com.test_meet.models.Building;
import com.test_meet.models.Floor;
import com.test_meet.models.MeetingRoom;
import com.test_meet.models.Reservation;
import com.test_meet.repositories.BuildingRepository;
import com.test_meet.repositories.FloorRepository;
import com.test_meet.repositories.MeetingRoomRepository;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

public class ReservationsForm extends JFrame {

  private JPanel contentPane;
  private JTextField startDateTextField;
  private JTextField textField;
  private JComboBox buildingComboBox;
  private JComboBox meetingRoomComboBox;

  /** Create the frame. */
  public ReservationsForm() throws ParseException {
    // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 450, 300);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);

    JLabel buildingLabel = new JLabel("Building: ");
    buildingLabel.setBounds(11, 17, 94, 14);
    contentPane.add(buildingLabel);

    // Initialize buildingComboBox
    buildingComboBox = new JComboBox();
    buildingComboBox.setBounds(115, 11, 314, 20);

    // Initialize floorComboBox
    JComboBox floorComboBox = new JComboBox();
    floorComboBox.setBounds(115, 41, 314, 20);

    // Initialize meetingRoomComboBox
    meetingRoomComboBox = new JComboBox();
    meetingRoomComboBox.setBounds(115, 72, 314, 20);
    // meetingRoomComboBox.addItem(new ComboItemDTO("Select item", "-1"));

    // Action listner when selected buildingComboBox item
    buildingComboBox.addActionListener(
        e -> {
          JComboBox comboBox = (JComboBox) e.getSource();
          ComboItemDTO o = (ComboItemDTO) comboBox.getSelectedItem();
          if (o != null) {
            floorComboBox.removeAllItems();
            // floorComboBox.addItem(new ComboItemDTO("Select item", "-1"));
            for (Floor floor :
                new FloorRepository().findAllByBuildingId(Integer.valueOf(o.getValue()))) {
              floorComboBox.addItem(
                  new ComboItemDTO(floor.getName(), String.valueOf(floor.getId())));
            }
          }
        });

    // Action listner when selected buildingComboBox item
    floorComboBox.addActionListener(
        e -> {
          JComboBox comboBox = (JComboBox) e.getSource();
          ComboItemDTO o = (ComboItemDTO) comboBox.getSelectedItem();
          if (o != null) {
            meetingRoomComboBox.removeAllItems();

            for (MeetingRoom meetingRoom :
                new MeetingRoomRepository().findAllByFloorId(Integer.valueOf(o.getValue()))) {
              meetingRoomComboBox.addItem(
                  new ComboItemDTO(meetingRoom.getName(), String.valueOf(meetingRoom.getId())));
            }
          }
        });

    // Filled buildingComboBox
    for (Building building : new BuildingRepository().findAll()) {
      buildingComboBox.addItem(
          new ComboItemDTO(building.getName(), String.valueOf(building.getId())));
    }

    contentPane.add(buildingComboBox);

    JLabel floorLabel = new JLabel("Floor: ");
    floorLabel.setBounds(11, 47, 94, 14);
    contentPane.add(floorLabel);

    contentPane.add(floorComboBox);

    JLabel meetingRoomLabel = new JLabel("Meeting Room: ");
    meetingRoomLabel.setBounds(11, 78, 94, 14);
    contentPane.add(meetingRoomLabel);

    contentPane.add(meetingRoomComboBox);

    JLabel startDateLabel_1 = new JLabel("Start Date: ");
    startDateLabel_1.setBounds(11, 109, 94, 14);
    contentPane.add(startDateLabel_1);

    Date date = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String dateString = formatter.format(date);
    startDateTextField = new JFormattedTextField(new MaskFormatter("####-##-## ##:##:##"));
    startDateTextField.setBounds(115, 103, 314, 20);
    startDateTextField.setColumns(10);
    startDateTextField.setText(dateString);

    contentPane.add(startDateTextField);

    textField = new JTextField();

    textField.addKeyListener(
        new KeyAdapter() {
          @Override
          public void keyPressed(KeyEvent e) {
            String value = textField.getText();

            textField.setText("");
            textField.setText(value.replaceAll("[^\\d.]", ""));
          }
        });

    textField.setBounds(115, 134, 314, 20);
    contentPane.add(textField);
    textField.setColumns(10);

    JLabel durationLabel = new JLabel("Duration: ");
    durationLabel.setBounds(11, 140, 94, 14);
    contentPane.add(durationLabel);

    JButton btnSave = new JButton("Save");
    btnSave.addActionListener(e -> submitForm());
    btnSave.setBounds(340, 165, 89, 23);
    contentPane.add(btnSave);
  }

  /** Submit form */
  public void submitForm() {
    Integer meetingRoomId = null;
    LocalDateTime start = null;
    LocalDateTime end = null;

    // Check that the inputs are filled
    try {
      meetingRoomId =
          Integer.parseInt(((ComboItemDTO) meetingRoomComboBox.getSelectedItem()).getValue());
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
      start = LocalDateTime.parse(startDateTextField.getText(), formatter);
      end = start.plusMinutes(Long.parseLong(textField.getText()));

    } catch (Exception e) {
      JOptionPane.showMessageDialog(
          contentPane, "Check that the inputs are filled", "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }

    // Store reservation
    try {
      new ReservationController().create(new Reservation(null, meetingRoomId, start, end));
    } catch (Exception e) {
      JOptionPane.showMessageDialog(
          contentPane, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }
  }
}
