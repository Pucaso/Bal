package human;

import java.util.Objects;

public class Human implements Comparable<Human> {
    private final String firstName;    // Имя
    private final String lastName;     // Фамилия
    private final int age;             // Возраст
    
    public Human(String firstName, String lastName, int age) {
        if (firstName == null || lastName == null) {
            throw new IllegalArgumentException("Имя и фамилия не могут быть null");
        }
        if (age < 0) {
            throw new IllegalArgumentException("Возраст не может быть отрицательным");
        }
        
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }
    
    // Геттеры
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public int getAge() { return age; }
    
    /**
     * Реализация Comparable<Human>
     * Сравнение по: имени (без учета регистра) -> фамилии (без учета регистра) -> возрасту
     */
    @Override
    public int compareTo(Human other) {
        if (this.equals(other)) {
            return 0;
        }
        
        // Сравнение по имени (без учета регистра)
        int result = this.firstName.compareToIgnoreCase(other.firstName);
        if (result != 0) {
            return result;
        }
        
        // Сравнение по фамилии (без учета регистра)
        result = this.lastName.compareToIgnoreCase(other.lastName);
        if (result != 0) {
            return result;
        }
        
        // Сравнение по возрасту
        return this.age - other.age;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Human human = (Human) obj;
        return age == human.age &&
               Objects.equals(firstName.toLowerCase(), human.firstName.toLowerCase()) &&
               Objects.equals(lastName.toLowerCase(), human.lastName.toLowerCase());
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(
            firstName.toLowerCase(), 
            lastName.toLowerCase(), 
            age
        );
    }
    
    @Override
    public String toString() {
        return String.format("%s %s (%d лет)", firstName, lastName, age);
    }
}