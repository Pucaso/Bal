package geometry2d;

import Exceptions.InvalidFigureException;
import java.util.logging.*;

public class Circle implements Figure {
    private static final Logger LOGGER = Logger.getLogger(Circle.class.getName());
    private double radius;
    
    static {
        setupLogger();
    }
    
    private static void setupLogger() {
        try {
            LOGGER.setLevel(Level.SEVERE);
            LOGGER.setUseParentHandlers(false);
            
            FileHandler fileHandler = new FileHandler("figures.log", true);
            fileHandler.setFormatter(new XMLFormatter());
            fileHandler.setLevel(Level.SEVERE);
            
            LOGGER.addHandler(fileHandler);
        } catch (Exception e) {
            System.err.println("Failed to setup logger for Circle: " + e.getMessage());
        }
    }
    
    public Circle(double radius) throws InvalidFigureException {
        long startTime = System.currentTimeMillis();
        LOGGER.severe("Creating Circle with radius: " + radius);
        
        try {
            setRadius(radius);
            LOGGER.severe("Circle created successfully");
        } catch (InvalidFigureException e) {
            LOGGER.severe("Failed to create Circle: " + e.getMessage());
            throw e;
        } finally {
            long endTime = System.currentTimeMillis();
            LOGGER.severe("Circle creation took " + (endTime - startTime) + " ms");
        }
    }
    
    private void setRadius(double radius) throws InvalidFigureException {
        if (radius <= 0) {
            throw new InvalidFigureException("Радиус круга должен быть положительным числом. Получено: " + radius);
        }
        this.radius = radius;
    }
    
    @Override
    public double Area() throws InvalidFigureException {
        LOGGER.severe("Calculating area for Circle with radius: " + radius);
        if (radius <= 0) {
            LOGGER.severe("Invalid radius when calculating area: " + radius);
            throw new InvalidFigureException("Невозможно вычислить площадь: радиус невалиден");
        }
        double area = Math.PI * radius * radius;
        LOGGER.severe("Circle area calculated: " + area);
        return area;
    }
    
    @Override
    public void Show() throws InvalidFigureException {
        LOGGER.severe("Displaying Circle information");
        System.out.println("Circle: radius = " + radius + ", area = " + String.format("%.2f", Area()));
        LOGGER.severe("Circle information displayed");
    }
    
    public double getRadius() {
        return radius;
    }
}