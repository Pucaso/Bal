package primes;

import java.util.Collections;
import java.util.List;

public class PrimesGeneratorTest {
    public static void main(String[] args) {
        System.out.println("=== ГЕНЕРАТОР ПРОСТЫХ ЧИСЕЛ ===\n");
        
        int n = 15;
        PrimesGenerator generator = new PrimesGenerator(n);
        
        // Прямой порядок с использованием Iterator
        System.out.println("Первые " + n + " простых чисел в прямом порядке:");
        System.out.print("Итератор: ");
        for (int prime : generator) {
            System.out.print(prime + " ");
        }
        System.out.println();
        
        // Получение списка и вывод в прямом порядке
        List<Integer> primesList = generator.getPrimesList();
        System.out.print("Список:   " + primesList);
        System.out.println();
        
        // Обратный порядок
        System.out.println("\nПервые " + n + " простых чисел в обратном порядке:");
        Collections.reverse(primesList);
        System.out.print("Результат: " + primesList);
        System.out.println();
        
        // Демонстрация работы с разными N
        System.out.println("\n--- Тестирование с разными значениями N ---");
        testWithDifferentN(5);
        testWithDifferentN(10);
        testWithDifferentN(8);
    }
    
    private static void testWithDifferentN(int n) {
        System.out.println("\nN = " + n + ":");
        PrimesGenerator gen = new PrimesGenerator(n);
        List<Integer> primes = gen.getPrimesList();
        System.out.println("Прямой порядок:  " + primes);
        Collections.reverse(primes);
        System.out.println("Обратный порядок: " + primes);
    }
}