package geometry2d;

import Exceptions.InvalidFigureException;
import java.util.logging.*;

public class Rectangle implements Figure {
    private static final Logger LOGGER = Logger.getLogger(Rectangle.class.getName());
    private double width;
    private double height;
    
    static {
        setupLogger();
    }
    
    private static void setupLogger() {
        try {
            LOGGER.setLevel(Level.INFO);
            LOGGER.setUseParentHandlers(false);
            
            FileHandler fileHandler = new FileHandler("figures.log", true);
            fileHandler.setFormatter(new XMLFormatter());
            fileHandler.setLevel(Level.INFO);
            
            LOGGER.addHandler(fileHandler);
        } catch (Exception e) {
            System.err.println("Failed to setup logger for Rectangle: " + e.getMessage());
        }
    }
    
    public Rectangle(double width, double height) throws InvalidFigureException {
        long startTime = System.currentTimeMillis();
        LOGGER.info("Creating Rectangle with width: " + width + ", height: " + height);
        
        try {
            setDimensions(width, height);
            LOGGER.info("Rectangle created successfully");
        } catch (InvalidFigureException e) {
            LOGGER.info("Failed to create Rectangle: " + e.getMessage());
            throw e;
        } finally {
            long endTime = System.currentTimeMillis();
            LOGGER.info("Rectangle creation took " + (endTime - startTime) + " ms");
        }
    }
    
    private void setDimensions(double width, double height) throws InvalidFigureException {
        if (width <= 0) {
            throw new InvalidFigureException("Ширина прямоугольника должна быть положительной. Получено: " + width);
        }
        if (height <= 0) {
            throw new InvalidFigureException("Высота прямоугольника должна быть положительной. Получено: " + height);
        }
        this.width = width;
        this.height = height;
    }
    
    @Override
    public double Area() throws InvalidFigureException {
        LOGGER.info("Calculating area for Rectangle with width: " + width + ", height: " + height);
        if (width <= 0 || height <= 0) {
            LOGGER.info("Invalid dimensions when calculating area");
            throw new InvalidFigureException("Невозможно вычислить площадь: размеры невалидны");
        }
        double area = width * height;
        LOGGER.info("Rectangle area calculated: " + area);
        return area;
    }
    
    @Override
    public void Show() throws InvalidFigureException {
        LOGGER.info("Displaying Rectangle information");
        System.out.println("Rectangle: width = " + width + ", height = " + height + ", area = " + String.format("%.2f", Area()));
        LOGGER.info("Rectangle information displayed");
    }
    
    public double getWidth() {
        return width;
    }
    
    public double getHeight() {
        return height;
    }
}