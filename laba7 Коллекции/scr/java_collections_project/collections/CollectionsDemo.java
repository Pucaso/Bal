package collections;

import java.util.*;
import java.util.stream.Collectors;

public class CollectionsDemo {
    
    public static void main(String[] args) {
        System.out.println("=== РАБОТА С КОЛЛЕКЦИЯМИ ===\n");
        
        // a) Создайте массив из N чисел
        int n = 15;
        System.out.println("a) Создание массива из " + n + " чисел:");
        Integer[] array = createArray(n);
        System.out.println("Массив: " + Arrays.toString(array));
        
        // b) На основе массива создайте список List
        System.out.println("\nb) Создание списка из массива:");
        List<Integer> list = createListFromArray(array);
        System.out.println("Список: " + list);
        
        // c) Отсортируйте список в натуральном порядке
        System.out.println("\nc) Сортировка в натуральном порядке:");
        List<Integer> sortedNatural = sortNatural(list);
        System.out.println("Результат: " + sortedNatural);
        
        // d) Отсортируйте список в обратном порядке
        System.out.println("\nd) Сортировка в обратном порядке:");
        List<Integer> sortedReverse = sortReverse(list);
        System.out.println("Результат: " + sortedReverse);
        
        // e) Перемешайте список
        System.out.println("\ne) Перемешивание списка:");
        List<Integer> shuffled = shuffleList(list);
        System.out.println("Результат: " + shuffled);
        
        // f) Выполните циклический сдвиг на 1 элемент
        System.out.println("\nf) Циклический сдвиг на 1 элемент:");
        List<Integer> rotated = rotateList(list, 1);
        System.out.println("Результат: " + rotated);
        
        // g) Оставьте в списке только уникальные элементы
        System.out.println("\ng) Только уникальные элементы:");
        List<Integer> unique = getUniqueElements(list);
        System.out.println("Результат: " + unique);
        
        // h) Оставьте в списке только дублирующиеся элементы
        System.out.println("\nh) Только дублирующиеся элементы:");
        List<Integer> duplicates = getDuplicateElements(list);
        System.out.println("Результат: " + duplicates);
        
        // i) Из списка получите массив
        System.out.println("\ni) Получение массива из списка:");
        Integer[] newArray = convertListToArray(list);
        System.out.println("Массив: " + Arrays.toString(newArray));
    }
    
    // a) Создание массива из N случайных чисел
    public static Integer[] createArray(int n) {
        Integer[] array = new Integer[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            array[i] = random.nextInt(10) + 1; // числа от 1 до 10 для создания дубликатов
        }
        return array;
    }
    
    // b) Создание списка из массива
    public static List<Integer> createListFromArray(Integer[] array) {
        return new ArrayList<>(Arrays.asList(array));
    }
    
    // c) Сортировка в натуральном порядке
    public static List<Integer> sortNatural(List<Integer> list) {
        List<Integer> copy = new ArrayList<>(list);
        Collections.sort(copy);
        return copy;
    }
    
    // d) Сортировка в обратном порядке
    public static List<Integer> sortReverse(List<Integer> list) {
        List<Integer> copy = new ArrayList<>(list);
        Collections.sort(copy, Collections.reverseOrder());
        return copy;
    }
    
    // e) Перемешивание списка
    public static List<Integer> shuffleList(List<Integer> list) {
        List<Integer> copy = new ArrayList<>(list);
        Collections.shuffle(copy);
        return copy;
    }
    
    // f) Циклический сдвиг
    public static List<Integer> rotateList(List<Integer> list, int distance) {
        List<Integer> copy = new ArrayList<>(list);
        Collections.rotate(copy, distance);
        return copy;
    }
    
    // g) Уникальные элементы
    public static List<Integer> getUniqueElements(List<Integer> list) {
        return list.stream()
                  .distinct()
                  .collect(Collectors.toList());
    }
    
    // h) Дублирующиеся элементы
    public static List<Integer> getDuplicateElements(List<Integer> list) {
        Map<Integer, Long> frequency = list.stream()
                                          .collect(Collectors.groupingBy(
                                              i -> i, 
                                              Collectors.counting()
                                          ));
        
        return list.stream()
                  .filter(i -> frequency.get(i) > 1)
                  .distinct()
                  .collect(Collectors.toList());
    }
    
    // i) Конвертация списка в массив
    public static Integer[] convertListToArray(List<Integer> list) {
        return list.toArray(new Integer[0]);
    }
}