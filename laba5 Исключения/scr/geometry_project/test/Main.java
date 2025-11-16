package test;

import Exceptions.InvalidCylinderException;
import Exceptions.InvalidFigureException;
import geometry2d.Circle;
import geometry2d.Figure;
import geometry2d.Rectangle;
import geometry3d.Cylinder;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Testing 2D Figures with Exception Handling ===");
        
        try {
            // Тестирование валидного круга
            Circle circle = new Circle(5.0);
            circle.Show();
            
            // Тестирование валидного прямоугольника
            Rectangle rectangle = new Rectangle(4.0, 6.0);
            rectangle.Show();
            
            System.out.println("\n=== Testing Invalid Figures ===");
            
            // Попытка создать невалидные фигуры
            try {
                Circle invalidCircle = new Circle(-2.0);
            } catch (InvalidFigureException e) {
                System.out.println("Ошибка создания круга: " + e.getMessage());
            }
            
            try {
                Rectangle invalidRectangle = new Rectangle(3.0, -1.0);
            } catch (InvalidFigureException e) {
                System.out.println("Ошибка создания прямоугольника: " + e.getMessage());
            }
            
            System.out.println("\n=== Testing 3D Cylinders ===");
            
            // Цилиндр с круглым основанием
            Cylinder cylinder1 = new Cylinder(circle, 10.0);
            cylinder1.Show();
            
            // Цилиндр с прямоугольным основанием
            Cylinder cylinder2 = new Cylinder(rectangle, 8.0);
            cylinder2.Show();
            
            System.out.println("\n=== Testing Invalid Cylinders ===");
            
            // Попытка создать невалидный цилиндр
            try {
                Cylinder invalidCylinder1 = new Cylinder(circle, -5.0);
            } catch (InvalidCylinderException | InvalidFigureException e) {
                System.out.println("Ошибка создания цилиндра: " + e.getMessage());
            }
            
            try {
                Cylinder invalidCylinder2 = new Cylinder(null, 5.0);
            } catch (InvalidCylinderException | InvalidFigureException e) {
                System.out.println("Ошибка создания цилиндра: " + e.getMessage());
            }
            
            System.out.println("\n=== Testing with Interface Reference ===");
            
            // Работа через интерфейс
            Figure fig1 = new Circle(3.0);
            Figure fig2 = new Rectangle(2.0, 5.0);
            
            fig1.Show();
            fig2.Show();
            
            Cylinder cylinder3 = new Cylinder(fig1, 7.0);
            cylinder3.Show();
            
            System.out.println("\n=== Testing Edge Cases ===");
            
            // Тестирование очень маленьких, но валидных значений
            try {
                Circle smallCircle = new Circle(0.1);
                smallCircle.Show();
            } catch (InvalidFigureException e) {
                System.out.println("Ошибка с маленьким кругом: " + e.getMessage());
            }
            
        } catch (InvalidFigureException | InvalidCylinderException e) {
            System.out.println("Непредвиденная ошибка: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Общая ошибка: " + e.getMessage());
            e.printStackTrace();
        }
        
        System.out.println("\n=== Program Completed ===");
    }
}