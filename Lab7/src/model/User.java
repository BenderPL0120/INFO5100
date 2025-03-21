package model;

public class User {
  private int userId;
  private String name;
  private int age;
  private String college;
  private String hobby;

  // 默认构造函数
  public User() {}

  // 带参数的构造函数
  public User(int userId, String name, int age, String college, String hobby) {
    this.userId = userId;
    this.name = name;
    this.age = age;
    this.college = college;
    this.hobby = hobby;
  }

  // Getter 和 Setter 方法
  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

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

  public String getCollege() {
    return college;
  }

  public void setCollege(String college) {
    this.college = college;
  }

  public String getHobby() {
    return hobby;
  }

  public void setHobby(String hobby) {
    this.hobby = hobby;
  }

  // toString 方法，方便调试和打印对象信息
  @Override
  public String toString() {
    return "User{" +
      "userId=" + userId +
      ", name='" + name + '\'' +
      ", age=" + age +
      ", college='" + college + '\'' +
      ", hobby='" + hobby + '\'' +
      '}';
  }
}
