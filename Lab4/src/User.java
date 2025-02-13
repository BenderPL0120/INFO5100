import java.awt.Image;
import java.util.Date;

public class User {
  private String firstName;
  private String lastName;
  private int age;
  private String email;
  private String hobby;
  private String gender;
  private Image photo;
  private Date birthday;

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

  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }

  public String getHobby() {
    return hobby;
  }
  public void setHobby(String hobby) {
    this.hobby = hobby;
  }

  public String getGender() {
    return gender;
  }
  public void setGender(String gender) {
    this.gender = gender;
  }

  public Image getPhoto() {
    return photo;
  }
  public void setPhoto(Image photo) {
    this.photo = photo;
  }

  public Date getBirthday() {
    return birthday;
  }
  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }
}