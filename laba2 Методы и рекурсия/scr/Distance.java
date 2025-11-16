public class Distance {
    public static double distance(double x1, double y1, double x2, double y2) {
        double dx = x2 - x1;  // разность по x
        double dy = y2 - y1;  // разность по y
        return Math.sqrt(dx * dx + dy * dy);  // теорема Пифагора
    }
    
    public static void main(String[] args) {
        // Пример использования
        double result = distance(0, 0, 3, 4);
        System.out.println("Расстояние: " + result);  // Выведет 5.0
        
        // Другие примеры
        System.out.println("Расстояние: " + distance(1, 1, 4, 5));  // (3,4) -> 5.0
        System.out.println("Расстояние: " + distance(0, 0, 1, 1));  // √2 ≈ 1.414
    }
}