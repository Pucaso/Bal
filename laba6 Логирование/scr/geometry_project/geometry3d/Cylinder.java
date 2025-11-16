package geometry3d;

import Exceptions.InvalidCylinderException;
import Exceptions.InvalidFigureException;
import geometry2d.Figure;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;

public class Cylinder {
    private static final Logger LOGGER = Logger.getLogger(Cylinder.class.getName());
    private Figure base;
    private double height;
    
    static {
        setupLogger();
    }
    
    private static void setupLogger() {
        try {
            LOGGER.setLevel(Level.FINEST);
            LOGGER.setUseParentHandlers(false);
            
            FileHandler fileHandler = new FileHandler("cylinder.log", true);
            fileHandler.setFormatter(new SimpleFormatter() {
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
            fileHandler.setLevel(Level.FINEST);
            
            LOGGER.addHandler(fileHandler);
        } catch (Exception e) {
            System.err.println("Failed to setup logger for Cylinder: " + e.getMessage());
        }
    }
    
    public Cylinder(Figure base, double height) throws InvalidCylinderException, InvalidFigureException {
        long startTime = System.currentTimeMillis();
        LOGGER.finest("Creating Cylinder with base: " + base.getClass().getSimpleName() + ", height: " + height);
        
        try {
            setBaseAndHeight(base, height);
            LOGGER.finest("Cylinder created successfully");
        } catch (InvalidCylinderException | InvalidFigureException e) {
            LOGGER.finest("Failed to create Cylinder: " + e.getMessage());
            throw e;
        } finally {
            long endTime = System.currentTimeMillis();
            LOGGER.finest("Cylinder creation took " + (endTime - startTime) + " ms");
        }
    }
    
    private void setBaseAndHeight(Figure base, double height) throws InvalidCylinderException, InvalidFigureException {
        if (base == null) {
            LOGGER.finest("Base is null when creating Cylinder");
            throw new InvalidCylinderException("Основание цилиндра не может быть null");
        }
        if (height <= 0) {
            LOGGER.finest("Invalid height when creating Cylinder: " + height);
            throw new InvalidCylinderException("Высота цилиндра должна быть положительной. Получено: " + height);
        }
        
        // Проверяем, что основание валидно
        base.Area();
        
        this.base = base;
        this.height = height;
        LOGGER.finest("Base and height set successfully");
    }
    
    public double Volume() throws InvalidCylinderException, InvalidFigureException {
        LOGGER.finest("Calculating volume for Cylinder");
        if (base == null) {
            LOGGER.finest("Base is null when calculating volume");
            throw new InvalidCylinderException("Невозможно вычислить объем: основание не задано");
        }
        if (height <= 0) {
            LOGGER.finest("Invalid height when calculating volume: " + height);
            throw new InvalidCylinderException("Невозможно вычислить объем: высота невалидна");
        }
        
        double volume = base.Area() * height;
        LOGGER.finest("Cylinder volume calculated: " + volume);
        return volume;
    }
    
    public void Show() throws InvalidCylinderException, InvalidFigureException {
        LOGGER.finest("Displaying Cylinder information");
        System.out.print("Cylinder: ");
        base.Show();
        System.out.println("         height = " + height + ", volume = " + String.format("%.2f", Volume()));
        LOGGER.finest("Cylinder information displayed");
    }
    
    public Figure getBase() {
        LOGGER.finest("Getting Cylinder base");
        return base;
    }
    
    public double getHeight() {
        LOGGER.finest("Getting Cylinder height");
        return height;
    }
}