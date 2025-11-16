public class PowerRecursive {
    public static double power(double a, int n) {
        // Базовый случай: любое число в степени 0 равно 1
        if (n == 0) {
            return 1.0;
        }
        
        // Рекурсивный случай: a^n = a * a^(n-1)
        return a * power(a, n - 1);
    }
    
    public static void main(String[] args) {
        // Тестирование функции
        System.out.println(power(2, 3));    // 8.0
        System.out.println(power(5, 0));    // 1.0
        System.out.println(power(10, 2));   // 100.0
        System.out.println(power(3, 4));    // 81.0
        System.out.println(power(7, 1));    // 7.0
    }
}