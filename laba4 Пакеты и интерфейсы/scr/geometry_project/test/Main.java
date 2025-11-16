package test;

import geometry2d.Circle;
import geometry2d.Rectangle;
import geometry2d.Figure;
import geometry3d.Cylinder;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Testing 2D Figures ===");
        
        // Тестирование круга
        Circle circle = new Circle(5.0);
        circle.Show();
        
        // Тестирование прямоугольника
        Rectangle rectangle = new Rectangle(4.0, 6.0);
        rectangle.Show();
        
        System.out.println("\n=== Testing 3D Cylinders ===");
        
        // Цилиндр с круглым основанием
        Cylinder cylinder1 = new Cylinder(circle, 10.0);
        cylinder1.Show();
        
        // Цилиндр с прямоугольным основанием
        Cylinder cylinder2 = new Cylinder(rectangle, 8.0);
        cylinder2.Show();
        
        System.out.println("\n=== Testing with Interface Reference ===");
        
        // Работа через интерфейс
        Figure fig1 = new Circle(3.0);
        Figure fig2 = new Rectangle(2.0, 5.0);
        
        fig1.Show();
        fig2.Show();
        
        Cylinder cylinder3 = new Cylinder(fig1, 7.0);
        cylinder3.Show();
    }
}