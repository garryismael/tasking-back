package com.godigital.tasking.person.domain;

public class Person {

  private String id;

  private String username;
  private String lastName;
  private int age;
  private String sex;

  public Person(
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

  public Person() {}

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
