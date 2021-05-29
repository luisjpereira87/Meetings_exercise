package com.test_meet.dto;

public class ComboItemDTO {
  private String key;
  private String value;

  public ComboItemDTO(String key, String value) {
    this.key = key;
    this.value = value;
  }

  @Override
  public String toString() {
    return key;
  }

  public String getKey() {
    return key;
  }

  public String getValue() {
    return value;
  }
}
