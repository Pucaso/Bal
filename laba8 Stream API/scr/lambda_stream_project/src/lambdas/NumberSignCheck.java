package lambdas;

import java.util.function.Function;

public class NumberSignCheck {
    public static void main(String[] args) {
        // Function для определения знака числа
        Function<Integer, String> numberSign = n -> {
            if (n == 0) {
                return "Ноль";
            } else if (n > 0) {
                return "Положительное число";
            } else {
                return "Отрицательное число";
            }
        };
        
        // Тестирование
        int[] testNumbers = {10, -5, 0, 100, -42, 7};
        
        System.out.println("=== Определение знака числа ===");
        for (int num : testNumbers) {
            System.out.println(num + " -> " + numberSign.apply(num));
        }
        
        // Расширенная версия с дополнительной информацией
        Function<Integer, String> detailedSign = n -> {
            if (n == 0) return "Ноль";
            String sign = n > 0 ? "Положительное" : "Отрицательное";
            return String.format("%s число (абсолютное значение: %d)", sign, Math.abs(n));
        };
        
        System.out.println("\n=== Детальная информация ===");
        for (int num : testNumbers) {
            System.out.println(num + " -> " + detailedSign.apply(num));
        }
    }
}