package com.godigital.tasking.person.domain;

import org.springframework.stereotype.Component;

@Component
public class PersonFactoryImpl implements PersonFactory {

  @Override
  public Person create(
    String id,
    String username,
    String lastName,
    int age,
    String sex
  ) {
    return new Person(id, username, lastName, age, sex);
  }
}
