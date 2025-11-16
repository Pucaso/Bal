package lambdas;

import java.util.Random;
import java.util.function.Supplier;

public class RandomSupplierDemo {
    public static void main(String[] args) {
        // Supplier для генерации случайного числа от 0 до 10
        Supplier<Integer> randomSupplier = () -> new Random().nextInt(11);
        
        // Альтернативная реализация с Math.random()
        Supplier<Integer> randomSupplier2 = () -> (int)(Math.random() * 11);
        
        System.out.println("=== Генерация случайных чисел ===");
        
        System.out.println("Случайные числа (Random):");
        for (int i = 0; i < 5; i++) {
            System.out.print(randomSupplier.get() + " ");
        }
        
        System.out.println("\n\nСлучайные числа (Math.random):");
        for (int i = 0; i < 5; i++) {
            System.out.print(randomSupplier2.get() + " ");
        }
        
        // Supplier для генерации массива случайных чисел
        Supplier<int[]> randomArraySupplier = () -> {
            int[] array = new int[5];
            Random rand = new Random();
            for (int i = 0; i < array.length; i++) {
                array[i] = rand.nextInt(11);
            }
            return array;
        };
        
        System.out.println("\n\nМассив случайных чисел:");
        int[] randomArray = randomArraySupplier.get();
        for (int num : randomArray) {
            System.out.print(num + " ");
        }
    }
}