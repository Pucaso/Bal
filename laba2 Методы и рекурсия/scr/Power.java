public class Power {
    public static double power(double a, int n) {
        // Обработка нулевой степени
        if (n == 0) {
            return 1.0;
        }
        
        // Обработка отрицательной степени
        if (n < 0) {
            return 1.0 / power(a, -n);
        }
        
        // Возведение в положительную степень
        double result = 1.0;
        for (int i = 0; i < n; i++) {
            result *= a;
        }
        return result;
    }
    
    public static void main(String[] args) {
        // Тестирование функции
        System.out.println(power(2, 3));    // 8.0
        System.out.println(power(2, -3));   // 0.125
        System.out.println(power(5, 0));    // 1.0
        System.out.println(power(10, 2));   // 100.0
        System.out.println(power(2, -1));   // 0.5
        System.out.println(power(3, 4));    // 81.0
    }
}