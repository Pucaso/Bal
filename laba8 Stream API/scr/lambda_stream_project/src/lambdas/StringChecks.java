package lambdas;

import java.util.function.Predicate;

public class StringChecks {
    public static void main(String[] args) {
        // a. Проверка, что строка не null
        Predicate<String> isNotNull = s -> s != null;
        
        // b. Проверка, что строка не пуста
        Predicate<String> isNotEmpty = s -> s != null && !s.isEmpty();
        
        // c. Комбинированная проверка с использованием and()
        Predicate<String> isNotNullAndNotEmpty = isNotNull.and(isNotEmpty);
        
        // Тестирование
        String test1 = null;
        String test2 = "";
        String test3 = "Hello";
        String test4 = "   ";
        
        System.out.println("=== Проверка пустой строки ===");
        System.out.println("null: isNotNull = " + isNotNull.test(test1) + 
                          ", isNotEmpty = " + isNotEmpty.test(test1) + 
                          ", combined = " + isNotNullAndNotEmpty.test(test1));
        System.out.println("пустая строка: isNotNull = " + isNotNull.test(test2) + 
                          ", isNotEmpty = " + isNotEmpty.test(test2) + 
                          ", combined = " + isNotNullAndNotEmpty.test(test2));
        System.out.println("Hello: isNotNull = " + isNotNull.test(test3) + 
                          ", isNotEmpty = " + isNotEmpty.test(test3) + 
                          ", combined = " + isNotNullAndNotEmpty.test(test3));
        System.out.println("пробелы: isNotNull = " + isNotNull.test(test4) + 
                          ", isNotEmpty = " + isNotEmpty.test(test4) + 
                          ", combined = " + isNotNullAndNotEmpty.test(test4));
        
        // Дополнительная проверка с пробелами
        Predicate<String> isNotBlank = s -> s != null && !s.trim().isEmpty();
        System.out.println("пробелы (isNotBlank): " + isNotBlank.test(test4));
    }
}