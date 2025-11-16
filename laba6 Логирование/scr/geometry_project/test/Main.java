package test;

import Exceptions.InvalidCylinderException;
import Exceptions.InvalidFigureException;
import geometry2d.Circle;
import geometry2d.Figure;
import geometry2d.Rectangle;
import geometry3d.Cylinder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    
    static {
        setupLogger();
    }
    
    private static void setupLogger() {
        try {
            LOGGER.setLevel(Level.FINE);
            LOGGER.setUseParentHandlers(false);
            
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setFormatter(new SimpleFormatter() {
                private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                
                @Override
                public String format(LogRecord record) {
                    return String.format("[%s] [%s] [%s] - %s%n",
                            dateFormat.format(new Date(record.getMillis())),
                            record.getLevel().getName(),
                            record.getLoggerName(),
                            record.getMessage());
                }
            });
            consoleHandler.setLevel(Level.FINE);
            
            LOGGER.addHandler(consoleHandler);
        } catch (Exception e) {
            System.err.println("Failed to setup logger for Main: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        LOGGER.fine("Starting the program");
        
        System.out.println("=== Testing 2D Figures with Exception Handling ===");
        
        try {
            LOGGER.fine("Testing valid figures creation");
            
            // Тестирование валидного круга
            Circle circle = new Circle(5.0);
            circle.Show();
            
            // Тестирование валидного прямоугольника
            Rectangle rectangle = new Rectangle(4.0, 6.0);
            rectangle.Show();
            
            System.out.println("\n=== Testing Invalid Figures ===");
            LOGGER.fine("Testing invalid figures creation");
            
            // Попытка создать невалидные фигуры
            try {
                Circle invalidCircle = new Circle(-2.0);
            } catch (InvalidFigureException e) {
                System.out.println("Ошибка создания круга: " + e.getMessage());
                LOGGER.fine("Caught expected exception for invalid circle: " + e.getMessage());
            }
            
            try {
                Rectangle invalidRectangle = new Rectangle(3.0, -1.0);
            } catch (InvalidFigureException e) {
                System.out.println("Ошибка создания прямоугольника: " + e.getMessage());
                LOGGER.fine("Caught expected exception for invalid rectangle: " + e.getMessage());
            }
            
            System.out.println("\n=== Testing 3D Cylinders ===");
            LOGGER.fine("Testing cylinder creation");
            
            // Цилиндр с круглым основанием
            Cylinder cylinder1 = new Cylinder(circle, 10.0);
            cylinder1.Show();
            
            // Цилиндр с прямоугольным основанием
            Cylinder cylinder2 = new Cylinder(rectangle, 8.0);
            cylinder2.Show();
            
            System.out.println("\n=== Testing Invalid Cylinders ===");
            LOGGER.fine("Testing invalid cylinder creation");
            
            // Попытка создать невалидный цилиндр
            try {
                Cylinder invalidCylinder1 = new Cylinder(circle, -5.0);
            } catch (InvalidCylinderException | InvalidFigureException e) {
                System.out.println("Ошибка создания цилиндра: " + e.getMessage());
                LOGGER.fine("Caught expected exception for invalid cylinder height: " + e.getMessage());
            }
            
            try {
                Cylinder invalidCylinder2 = new Cylinder(null, 5.0);
            } catch (InvalidCylinderException | InvalidFigureException e) {
                System.out.println("Ошибка создания цилиндра: " + e.getMessage());
                LOGGER.fine("Caught expected exception for null cylinder base: " + e.getMessage());
            }
            
            System.out.println("\n=== Testing with Interface Reference ===");
            LOGGER.fine("Testing interface usage");
            
            // Работа через интерфейс
            Figure fig1 = new Circle(3.0);
            Figure fig2 = new Rectangle(2.0, 5.0);
            
            fig1.Show();
            fig2.Show();
            
            Cylinder cylinder3 = new Cylinder(fig1, 7.0);
            cylinder3.Show();
            
            System.out.println("\n=== Testing Edge Cases ===");
            LOGGER.fine("Testing edge cases");
            
            // Тестирование очень маленьких, но валидных значений
            try {
                Circle smallCircle = new Circle(0.1);
                smallCircle.Show();
                LOGGER.fine("Successfully created circle with small radius");
            } catch (InvalidFigureException e) {
                System.out.println("Ошибка с маленьким кругом: " + e.getMessage());
                LOGGER.fine("Failed to create circle with small radius: " + e.getMessage());
            }
            
        } catch (InvalidFigureException | InvalidCylinderException e) {
            System.out.println("Непредвиденная ошибка: " + e.getMessage());
            LOGGER.fine("Unexpected error: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Общая ошибка: " + e.getMessage());
            LOGGER.fine("General error: " + e.getMessage());
            e.printStackTrace();
        }
        
        LOGGER.fine("Program completed successfully");
        System.out.println("\n=== Program Completed ===");
    }
}