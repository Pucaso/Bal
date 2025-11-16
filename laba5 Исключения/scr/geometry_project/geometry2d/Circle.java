package geometry2d;

import Exceptions.InvalidFigureException;

public class Circle implements Figure {
    private double radius;
    
    public Circle(double radius) throws InvalidFigureException {
        setRadius(radius);
    }
    
    private void setRadius(double radius) throws InvalidFigureException {
        if (radius <= 0) {
            throw new InvalidFigureException("Радиус круга должен быть положительным числом. Получено: " + radius);
        }
        this.radius = radius;
    }
    
    @Override
    public double Area() throws InvalidFigureException {
        if (radius <= 0) {
            throw new InvalidFigureException("Невозможно вычислить площадь: радиус невалиден");
        }
        return Math.PI * radius * radius;
    }
    
    @Override
    public void Show() throws InvalidFigureException {
        System.out.println("Circle: radius = " + radius + ", area = " + String.format("%.2f", Area()));
    }
    
    public double getRadius() {
        return radius;
    }
}