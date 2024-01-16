package com.godigital.tasking.person.db;

import com.godigital.tasking.person.domain.Person;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "persons")
public class PersonDataMapper {

  @Id
  private String id;

  private String username;
  private String lastName;
  private int age;
  private String sex;

  public PersonDataMapper() {}

  public PersonDataMapper(
    String id,
    String username,
    String lastName,
    int age,
    String sex
  ) {
    this.id = id;
    this.username = username;
    this.lastName = lastName;
    this.age = age;
    this.sex = sex;
  }

  public PersonDataMapper(Person person) {
    this.id = person.getId();
    this.username = person.getUsername();
    this.lastName = person.getLastName();
    this.age = person.getAge();
    this.sex = person.getSex();
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }
}
