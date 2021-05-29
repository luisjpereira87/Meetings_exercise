package com.test_meet.repositories;

import java.util.List;

public interface Repository<T> {
  public T findById(Integer id);

  public List<T> findAll();

  public void create(T value);
}
