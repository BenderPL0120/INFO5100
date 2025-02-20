package model;

public class User {
  private String name;
  private int age;
  private String email;

  // Getters/Setters
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }
  public void setAge(int age) {
    this.age = age;
  }

  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }

  public static void validate(String name, String ageStr, String email) throws IllegalArgumentException {
    // name validation
    if (name == null || name.trim().isEmpty()) {
      throw new IllegalArgumentException("Name cannot be empty");
    }
    if (!name.matches("^[a-zA-Z]+$")) {
      throw new IllegalArgumentException("Name must contain only letters");
    }

    // age validation
    try {
      int age = Integer.parseInt(ageStr);
      if (age < 18 || age > 120) {
        throw new IllegalArgumentException("Age must be between 18 and 120");
      }
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Age must be a number");
    }

    // email validation
    if (!email.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
      throw new IllegalArgumentException("Email format error");
    }
  }
}
