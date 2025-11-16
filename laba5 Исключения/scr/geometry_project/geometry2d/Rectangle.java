package geometry2d;

import Exceptions.InvalidFigureException;

public class Rectangle implements Figure {
    private double width;
    private double height;
    
    public Rectangle(double width, double height) throws InvalidFigureException {
        setDimensions(width, height);
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
        if (width <= 0 || height <= 0) {
            throw new InvalidFigureException("Невозможно вычислить площадь: размеры невалидны");
        }
        return width * height;
    }
    
    @Override
    public void Show() throws InvalidFigureException {
        System.out.println("Rectangle: width = " + width + ", height = " + height + ", area = " + String.format("%.2f", Area()));
    }
    
    public double getWidth() {
        return width;
    }
    
    public double getHeight() {
        return height;
    }
}