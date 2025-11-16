package geometry3d;

import Exceptions.InvalidCylinderException;
import Exceptions.InvalidFigureException;
import geometry2d.Figure;

public class Cylinder {
    private Figure base;
    private double height;
    
    public Cylinder(Figure base, double height) throws InvalidCylinderException, InvalidFigureException {
        setBaseAndHeight(base, height);
    }
    
    private void setBaseAndHeight(Figure base, double height) throws InvalidCylinderException, InvalidFigureException {
        if (base == null) {
            throw new InvalidCylinderException("Основание цилиндра не может быть null");
        }
        if (height <= 0) {
            throw new InvalidCylinderException("Высота цилиндра должна быть положительной. Получено: " + height);
        }
        
        // Проверяем, что основание валидно
        base.Area(); // Если выбросит исключение - передаем дальше
        
        this.base = base;
        this.height = height;
    }
    
    public double Volume() throws InvalidCylinderException, InvalidFigureException {
        if (base == null) {
            throw new InvalidCylinderException("Невозможно вычислить объем: основание не задано");
        }
        if (height <= 0) {
            throw new InvalidCylinderException("Невозможно вычислить объем: высота невалидна");
        }
        
        return base.Area() * height;
    }
    
    public void Show() throws InvalidCylinderException, InvalidFigureException {
        System.out.print("Cylinder: ");
        base.Show();
        System.out.println("         height = " + height + ", volume = " + String.format("%.2f", Volume()));
    }
    
    public Figure getBase() {
        return base;
    }
    
    public double getHeight() {
        return height;
    }
}