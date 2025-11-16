public class Tribonacci {
    public static int tribonacci(int n) {
        // Базовые случаи
        if (n == 0 || n == 1) {
            return 0;
        }
        if (n == 2) {
            return 1;
        }
        
        // Рекурсивный случай: T(n) = T(n-1) + T(n-2) + T(n-3)
        return tribonacci(n - 1) + tribonacci(n - 2) + tribonacci(n - 3);
    }
    
    public static void main(String[] args) {
        // Тестирование функции
        System.out.println("T(0) = " + tribonacci(0));  // 0
        System.out.println("T(1) = " + tribonacci(1));  // 0
        System.out.println("T(2) = " + tribonacci(2));  // 1
        System.out.println("T(3) = " + tribonacci(3));  // 1 (0+0+1)
        System.out.println("T(4) = " + tribonacci(4));  // 2 (0+1+1)
        System.out.println("T(5) = " + tribonacci(5));  // 4 (1+1+2)
        System.out.println("T(6) = " + tribonacci(6));  // 7 (1+2+4)
        System.out.println("T(7) = " + tribonacci(7));  // 13 (2+4+7)
    }
}