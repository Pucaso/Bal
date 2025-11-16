package lambdas;

import java.util.function.Predicate;

public class StringPatternCheck {
    public static void main(String[] args) {
        // Проверка, что строка начинается с "J" или "N" и заканчивается "A"
        Predicate<String> patternCheck = s -> {
            if (s == null || s.length() < 2) return false;
            
            char firstChar = Character.toUpperCase(s.charAt(0));
            char lastChar = Character.toUpperCase(s.charAt(s.length() - 1));
            
            return (firstChar == 'J' || firstChar == 'N') && lastChar == 'A';
        };
        
        // Тестирование
        String[] testStrings = {
            "Java", "Neta", "JavaScript", "Nova", "Python", 
            "C++", "JA", "jAva", "nA", null, ""
        };
        
        System.out.println("=== Проверка паттерна строки ===");
        for (String str : testStrings) {
            System.out.printf("'%s' -> %b%n", str, patternCheck.test(str));
        }
    }
}