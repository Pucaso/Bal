package human;

import java.util.*;

public class HumanTest {
    public static void main(String[] args) {
        System.out.println("=== ТЕСТИРОВАНИЕ КЛАССА HUMAN ===\n");
        
        // Создание коллекции на основе HashSet
        Set<Human> humanSet = new HashSet<>();
        
        // Добавление людей в коллекцию
        System.out.println("Добавление людей в HashSet:");
        humanSet.add(new Human("Иван", "Петров", 25));
        humanSet.add(new Human("Мария", "Иванова", 30));
        humanSet.add(new Human("Петр", "Сидоров", 22));
        humanSet.add(new Human("Анна", "Кузнецова", 28));
        humanSet.add(new Human("Сергей", "Васильев", 35));
        
        // Попытка добавить дубликат (не добавится из-за equals/hashCode)
        Human ivanDuplicate = new Human("Иван", "Петров", 25);
        humanSet.add(ivanDuplicate);
        
        System.out.println("Размер коллекции: " + humanSet.size());
        System.out.println("Содержит дубликат Ивана: " + humanSet.contains(ivanDuplicate));
        
        // Вывод коллекции на консоль
        System.out.println("\nСодержимое HashSet (порядок может быть произвольным):");
        for (Human human : humanSet) {
            System.out.println("  " + human);
        }
        
        // Сортировка с использованием Comparable (естественный порядок)
        System.out.println("\n--- Сортировка с использованием Comparable ---");
        List<Human> sortedByNatural = new ArrayList<>(humanSet);
        Collections.sort(sortedByNatural);
        
        System.out.println("Отсортировано по имени->фамилии->возрасту:");
        for (int i = 0; i < sortedByNatural.size(); i++) {
            System.out.println((i + 1) + ". " + sortedByNatural.get(i));
        }
        
        // Сортировка с использованием компаратора по фамилии
        System.out.println("\n--- Сортировка с использованием HumanComparatorByLName ---");
        List<Human> sortedByLastName = new ArrayList<>(humanSet);
        Collections.sort(sortedByLastName, new HumanComparatorByLName());
        
        System.out.println("Отсортировано только по фамилии:");
        for (int i = 0; i < sortedByLastName.size(); i++) {
            System.out.println((i + 1) + ". " + sortedByLastName.get(i));
        }
        
        // Дополнительные тесты
        System.out.println("\n--- Дополнительные тесты ---");
        testComparison();
        testEdgeCases();
    }
    
    private static void testComparison() {
        System.out.println("\nТестирование сравнения:");
        
        Human h1 = new Human("Иван", "Петров", 25);
        Human h2 = new Human("иван", "петров", 25);  // тот же человек (без учета регистра)
        Human h3 = new Human("Иван", "Петров", 30);  // другой возраст
        Human h4 = new Human("Петр", "Петров", 25);  // другое имя
        
        System.out.println("h1.equals(h2): " + h1.equals(h2) + " (ожидается: true)");
        System.out.println("h1.compareTo(h2): " + h1.compareTo(h2) + " (ожидается: 0)");
        System.out.println("h1.compareTo(h3): " + h1.compareTo(h3) + " (ожидается: отрицательное)");
        System.out.println("h1.compareTo(h4): " + h1.compareTo(h4) + " (ожидается: отрицательное)");
    }
    
    private static void testEdgeCases() {
        System.out.println("\nТестирование граничных случаев:");
        
        try {
            Human invalid = new Human(null, "Тест", 25);
        } catch (IllegalArgumentException e) {
            System.out.println("Поймано ожидаемое исключение: " + e.getMessage());
        }
        
        try {
            Human invalid = new Human("Тест", null, 25);
        } catch (IllegalArgumentException e) {
            System.out.println("Поймано ожидаемое исключение: " + e.getMessage());
        }
        
        try {
            Human invalid = new Human("Тест", "Тест", -1);
        } catch (IllegalArgumentException e) {
            System.out.println("Поймано ожидаемое исключение: " + e.getMessage());
        }
    }
}