package model;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private int id;
    private String name;
    private int age;
    private List<Phone> phones;
    
    public Client(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.phones = new ArrayList<>();
    }
    
    // Геттеры и сеттеры
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    
    public List<Phone> getPhones() { return phones; }
    public void setPhones(List<Phone> phones) { this.phones = phones; }
    
    public void addPhone(Phone phone) {
        phones.add(phone);
    }
    
    @Override
    public String toString() {
        return "Client{id=" + id + ", name='" + name + "', age=" + age + 
               ", phones=" + phones + "}";
    }
}