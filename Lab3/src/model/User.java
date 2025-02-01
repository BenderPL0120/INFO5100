package model;

import javax.swing.*;

public class User {
  private String firstName;
  private String lastName;
  private int age;
  private String phoneNumber;
  private String email;
  private String gender;

  private String hobbies;
  private ImageIcon photo;

  // Getters and Setters
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
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

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getHobbies() {
    return hobbies;
  }

  public void setHobbies(String hobbies) {
    this.hobbies = hobbies;
  }

  public ImageIcon getPhoto() {
    return photo;
  }

  public void setPhoto(ImageIcon photo) {
    this.photo = photo;
  }

  // toString method
  @Override
  public String toString() {
    return "User Profile:\n" +
            "First Name: " + firstName + "\n" +
            "Last Name: " + lastName + "\n" +
            "Age: " + age + "\n" +
            "Phone Number: " + phoneNumber + "\n" +
            "Email: " + email + "\n" +
            "Gender: " + gender + "\n" +
            "Hobbies: " + hobbies + "\n" +
            "Photo: " + (photo != null ? "Uploaded" : "Not Uploaded");
  }
}
