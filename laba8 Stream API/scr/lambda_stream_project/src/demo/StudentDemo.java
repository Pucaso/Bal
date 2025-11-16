package demo;

import model.Student;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class StudentDemo {
    
    // Создание тестовых данных
    private static List<Student> createStudents() {
        return Arrays.asList(
            new Student(1, "Иванов", "Иван", "Иванович", 
                       LocalDate.of(2000, 5, 15), "ул. Пушкина, 10", 
                       "+7-911-111-11-11", "ФИИТ", 2, "ФИИТ-2-1"),
            new Student(2, "Петров", "Петр", "Петрович", 
                       LocalDate.of(2001, 3, 20), "ул. Лермонтова, 20", 
                       "+7-911-222-22-22", "ИВТ", 1, "ИВТ-1-2"),
            new Student(3, "Сидорова", "Мария", "Сергеевна", 
                       LocalDate.of(1999, 12, 5), "ул. Гоголя, 30", 
                       "+7-911-333-33-33", "ФИИТ", 2, "ФИИТ-2-2"),
            new Student(4, "Кузнецов", "Алексей", "Васильевич", 
                       LocalDate.of(2002, 7, 10), "ул. Толстого, 40", 
                       "+7-911-444-44-44", "ИВТ", 1, "ИВТ-1-1"),
            new Student(5, "Николаева", "Ольга", "Дмитриевна", 
                       LocalDate.of(2000, 1, 25), "ул. Чехова, 50", 
                       "+7-911-555-55-55", "ФИИТ", 3, "ФИИТ-3-1"),
            new Student(6, "Васильев", "Дмитрий", "Игоревич", 
                       LocalDate.of(2001, 9, 30), "ул. Достоевского, 60", 
                       "+7-911-666-66-66", "ИВТ", 2, "ИВТ-2-1")
        );
    }
    
    public static void main(String[] args) {
        List<Student> students = createStudents();
        
        System.out.println("=== Все студенты ===");
        students.forEach(System.out::println);
        
        // a. Список студентов заданного факультета
        String faculty = "ФИИТ";
        System.out.println("\n=== Студенты факультета '" + faculty + "' ===\n");
        
        // Способ 1: циклы и операторы условия
        System.out.println("Способ 1 (циклы):");
        List<Student> facultyStudents1 = new ArrayList<>();
        for (Student student : students) {
            if (faculty.equals(student.getFaculty())) {
                facultyStudents1.add(student);
            }
        }
        facultyStudents1.forEach(System.out::println);
        
        // Способ 2: методы коллекций
        System.out.println("\nСпособ 2 (методы коллекций):");
        List<Student> facultyStudents2 = new ArrayList<>(students);
        facultyStudents2.removeIf(student -> !faculty.equals(student.getFaculty()));
        facultyStudents2.forEach(System.out::println);
        
        // Способ 3: Stream API
        System.out.println("\nСпособ 3 (Stream API):");
        List<Student> facultyStudents3 = students.stream()
            .filter(student -> faculty.equals(student.getFaculty()))
            .collect(Collectors.toList());
        facultyStudents3.forEach(System.out::println);
        
        // b. Списки студентов для каждого факультета и курса
        System.out.println("\n=== Студенты по факультетам и курсам ===\n");
        
        // Способ 1: циклы
        System.out.println("Способ 1 (циклы):");
        Map<String, Map<Integer, List<Student>>> byFacultyAndCourse1 = new HashMap<>();
        for (Student student : students) {
            byFacultyAndCourse1
                .computeIfAbsent(student.getFaculty(), k -> new HashMap<>())
                .computeIfAbsent(student.getCourse(), k -> new ArrayList<>())
                .add(student);
        }
        byFacultyAndCourse1.forEach((fac, courseMap) -> {
            System.out.println("Факультет: " + fac);
            courseMap.forEach((course, studentList) -> {
                System.out.println("  Курс " + course + ": " + 
                    studentList.stream().map(Student::getLastName).collect(Collectors.joining(", ")));
            });
        });
        
        // Способ 2: Stream API
        System.out.println("\nСпособ 2 (Stream API):");
        Map<String, Map<Integer, List<Student>>> byFacultyAndCourse2 = students.stream()
            .collect(Collectors.groupingBy(
                Student::getFaculty,
                Collectors.groupingBy(Student::getCourse)
            ));
        byFacultyAndCourse2.forEach((fac, courseMap) -> {
            System.out.println("Факультет: " + fac);
            courseMap.forEach((course, studentList) -> {
                System.out.println("  Курс " + course + ": " + 
                    studentList.stream().map(Student::getLastName).collect(Collectors.joining(", ")));
            });
        });
        
        // c. Список студентов, родившихся после заданного года
        int year = 2000;
        System.out.println("\n=== Студенты, родившиеся после " + year + " года ===\n");
        
        // Способ 1: циклы
        System.out.println("Способ 1 (циклы):");
        List<Student> bornAfter1 = new ArrayList<>();
        for (Student student : students) {
            if (student.getBirthDate().getYear() > year) {
                bornAfter1.add(student);
            }
        }
        bornAfter1.forEach(System.out::println);
        
        // Способ 2: методы коллекций
        System.out.println("\nСпособ 2 (методы коллекций):");
        List<Student> bornAfter2 = new ArrayList<>(students);
        bornAfter2.removeIf(student -> student.getBirthDate().getYear() <= year);
        bornAfter2.forEach(System.out::println);
        
        // Способ 3: Stream API
        System.out.println("\nСпособ 3 (Stream API):");
        List<Student> bornAfter3 = students.stream()
            .filter(student -> student.getBirthDate().getYear() > year)
            .collect(Collectors.toList());
        bornAfter3.forEach(System.out::println);
    }
}