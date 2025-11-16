package model;

import java.time.LocalDate;
import java.util.Objects;

public class Student {
    private int id;
    private String lastName;
    private String firstName;
    private String middleName;
    private LocalDate birthDate;
    private String address;
    private String phone;
    private String faculty;
    private int course;
    private String group;
    
    // Конструкторы
    public Student() {}
    
    public Student(int id, String lastName, String firstName, String faculty, int course) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.faculty = faculty;
        this.course = course;
    }
    
    public Student(int id, String lastName, String firstName, String middleName, 
                   LocalDate birthDate, String address, String phone, 
                   String faculty, int course, String group) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.birthDate = birthDate;
        this.address = address;
        this.phone = phone;
        this.faculty = faculty;
        this.course = course;
        this.group = group;
    }
    
    // Геттеры и сеттеры
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    
    public String getMiddleName() { return middleName; }
    public void setMiddleName(String middleName) { this.middleName = middleName; }
    
    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }
    
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    
    public String getFaculty() { return faculty; }
    public void setFaculty(String faculty) { this.faculty = faculty; }
    
    public int getCourse() { return course; }
    public void setCourse(int course) { this.course = course; }
    
    public String getGroup() { return group; }
    public void setGroup(String group) { this.group = group; }
    
    // Вспомогательный метод для получения года рождения
    public int getBirthYear() {
        return birthDate != null ? birthDate.getYear() : 0;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return String.format("Student{id=%d, name='%s %s %s', birthDate=%s, faculty='%s', course=%d, group='%s'}",
                id, lastName, firstName, middleName != null ? middleName : "", 
                birthDate, faculty, course, group);
    }
}