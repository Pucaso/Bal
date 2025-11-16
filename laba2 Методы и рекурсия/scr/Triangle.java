public class Triangle {
    public static void triangle(int a, int b, int c) {
        if (a + b > c && a + c > b && b + c > a) {
            System.out.println("Это треугольник");
        } else {
            System.out.println("Это не треугольник");
        }
    }

    // Для тестирования
    public static void main(String[] args) {
        // Примеры из задания
        triangle(1, 1, 2);    // Это не треугольник
        triangle(7, 6, 10);   // Это треугольник
        triangle(20, 13, 17); // Это треугольник
        
        // Дополнительные тесты
        triangle(3, 4, 5);    // Это треугольник
        triangle(1, 2, 3);    // Это не треугольник (сумма равна третьей стороне)
    }
}