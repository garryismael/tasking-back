package com.godigital.tasking.person.domain;

public interface PersonFactory {
  Person create(
    String id,
    String username,
    String lastName,
    int age,
    String sex
  );
}
