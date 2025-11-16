package streamapi;

import java.util.*;
import java.util.stream.Collectors;

public class StreamMethods {
    
    // a. Метод, возвращающий среднее значение списка целых чисел
    public static OptionalDouble average(List<Integer> numbers) {
        return numbers.stream()
                .mapToInt(Integer::intValue)
                .average();
    }
    
    // b. Метод, приводящий все строки в верхний регистр и добавляющий префикс «new»
    public static List<String> toUpperCaseWithPrefix(List<String> strings) {
        return strings.stream()
                .map(s -> "new " + s.toUpperCase())
                .collect(Collectors.toList());
    }
    
    // c. Метод, возвращающий список квадратов всех встречающихся только один раз элементов
    public static List<Integer> squaresOfUnique(List<Integer> numbers) {
        return numbers.stream()
                .collect(Collectors.groupingBy(n -> n, Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() == 1)
                .map(entry -> entry.getKey() * entry.getKey())
                .collect(Collectors.toList());
    }
    
    // d. Метод, возвращающий все строки, начинающиеся с заданной буквы, отсортированные по алфавиту
    public static List<String> filterAndSort(Collection<String> strings, char letter) {
        return strings.stream()
                .filter(s -> s != null && !s.isEmpty() && 
                           Character.toLowerCase(s.charAt(0)) == Character.toLowerCase(letter))
                .sorted()
                .collect(Collectors.toList());
    }
    
    // e. Метод, возвращающий последний элемент коллекции или кидающий исключение
    public static <T> T getLastElement(Collection<T> collection) {
        return collection.stream()
                .reduce((first, second) -> second)
                .orElseThrow(() -> new NoSuchElementException("Коллекция пуста"));
    }
    
    // f. Метод, возвращающий сумму чётных чисел массива
    public static int sumEvenNumbers(int[] numbers) {
        return Arrays.stream(numbers)
                .filter(n -> n % 2 == 0)
                .sum();
    }
    
    // g. Метод, преобразовывающий все строки в Map (первый символ – ключ, оставшиеся – значение)
    public static Map<Character, String> toMap(List<String> strings) {
        return strings.stream()
                .filter(s -> s != null && !s.isEmpty())
                .collect(Collectors.toMap(
                    s -> s.charAt(0),
                    s -> s.length() > 1 ? s.substring(1) : "",
                    (existing, replacement) -> existing + "," + replacement
                ));
    }
    
    public static void main(String[] args) {
        System.out.println("=== Демонстрация методов Stream API ===\n");
        
        // Тестирование метода a
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println("a) Среднее значение " + numbers + ": " + 
                          average(numbers).orElse(0));
        
        // Тестирование метода b
        List<String> strings = Arrays.asList("apple", "banana", "cherry");
        System.out.println("b) Строки с префиксом: " + toUpperCaseWithPrefix(strings));
        
        // Тестирование метода c
        List<Integer> numbersWithDuplicates = Arrays.asList(1, 2, 3, 2, 4, 3, 5);
        System.out.println("c) Квадраты уникальных из " + numbersWithDuplicates + ": " + 
                          squaresOfUnique(numbersWithDuplicates));
        
        // Тестирование метода d
        Collection<String> stringCollection = Arrays.asList("apple", "Banana", "apricot", "cherry", "avocado");
        System.out.println("d) Строки на 'a': " + filterAndSort(stringCollection, 'a'));
        
        // Тестирование метода e
        List<String> nonEmptyList = Arrays.asList("first", "second", "last");
        System.out.println("e) Последний элемент: " + getLastElement(nonEmptyList));
        
        try {
            List<String> emptyList = new ArrayList<>();
            getLastElement(emptyList);
        } catch (NoSuchElementException e) {
            System.out.println("e) Исключение для пустой коллекции: " + e.getMessage());
        }
        
        // Тестирование метода f
        int[] array = {1, 2, 3, 4, 5, 6};
        System.out.println("f) Сумма чётных чисел " + Arrays.toString(array) + ": " + 
                          sumEvenNumbers(array));
        
        // Тестирование метода g
        List<String> mapStrings = Arrays.asList("apple", "apricot", "banana", "berry", "avocado");
        System.out.println("g) Map из строк: " + toMap(mapStrings));
    }
}