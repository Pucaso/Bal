import java.util.ArrayList;
import java.util.List;

// Задание 1: Класс Button
class Button {
    private int clickCount;
    
    public Button() {
        this.clickCount = 0;
    }
    
    public void click() {
        clickCount++;
        System.out.println("Button was clicked " + clickCount + " times");
    }
}

// Задание 2: Класс Balance
class Balance {
    private int leftWeight;
    private int rightWeight;
    
    public Balance() {
        this.leftWeight = 0;
        this.rightWeight = 0;
    }
    
    public void addLeft(int weight) {
        leftWeight += weight;
    }
    
    public void addRight(int weight) {
        rightWeight += weight;
    }
    
    public char result() {
        if (leftWeight == rightWeight) {
            return '=';
        } else if (rightWeight > leftWeight) {
            return 'R';
        } else {
            return 'L';
        }
    }
}

// Задание 3: Класс Bell
class Bell {
    private boolean isDing = true;
    
    public void sound() {
        if (isDing) {
            System.out.println("ding");
        } else {
            System.out.println("dong");
        }
        isDing = !isDing;
    }
}

// Задание 4: Класс OddEvenSeparator
class OddEvenSeparator {
    private List<Integer> numbers;
    
    public OddEvenSeparator() {
        this.numbers = new ArrayList<>();
    }
    
    public void addNumber(int number) {
        numbers.add(number);
    }
    
    public void even() {
        System.out.print("Even numbers: ");
        for (int num : numbers) {
            if (num % 2 == 0) {
                System.out.print(num + " ");
            }
        }
        System.out.println();
    }
    
    public void odd() {
        System.out.print("Odd numbers: ");
        for (int num : numbers) {
            if (num % 2 != 0) {
                System.out.print(num + " ");
            }
        }
        System.out.println();
    }
}

// Задание 5: Класс Table
class Table {
    private int[][] data;
    private int rows;
    private int cols;
    
    public Table(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.data = new int[rows][cols];
        // Инициализация нулями (в Java массив int уже инициализируется нулями)
    }
    
    public int getValue(int row, int col) {
        return data[row][col];
    }
    
    public void setValue(int row, int col, int value) {
        data[row][col] = value;
    }
    
    public int rows() {
        return rows;
    }
    
    public int cols() {
        return cols;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sb.append(data[i][j]);
                if (j < cols - 1) {
                    sb.append(" ");
                }
            }
            if (i < rows - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
    
    public double average() {
        if (rows == 0 || cols == 0) {
            return 0.0;
        }
        
        int sum = 0;
        int count = 0;
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sum += data[i][j];
                count++;
            }
        }
        
        return (double) sum / count;
    }
}

// Главный класс для демонстрации
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Задание 1: Button ===");
        Button button = new Button();
        button.click();
        button.click();
        button.click();
        
        System.out.println("\n=== Задание 2: Balance ===");
        Balance balance = new Balance();
        balance.addLeft(10);
        balance.addRight(5);
        System.out.println("Result: " + balance.result()); // L
        
        balance.addRight(5);
        System.out.println("Result: " + balance.result()); // =
        
        balance.addRight(5);
        System.out.println("Result: " + balance.result()); // R
        
        System.out.println("\n=== Задание 3: Bell ===");
        Bell bell = new Bell();
        bell.sound(); // ding
        bell.sound(); // dong
        bell.sound(); // ding
        bell.sound(); // dong
        
        System.out.println("\n=== Задание 4: OddEvenSeparator ===");
        OddEvenSeparator separator = new OddEvenSeparator();
        separator.addNumber(1);
        separator.addNumber(2);
        separator.addNumber(3);
        separator.addNumber(4);
        separator.addNumber(5);
        separator.addNumber(6);
        
        separator.even(); // Even numbers: 2 4 6
        separator.odd();  // Odd numbers: 1 3 5
        
        System.out.println("\n=== Задание 5: Table ===");
        Table table = new Table(3, 4);
        
        // Заполняем таблицу значениями
        int value = 1;
        for (int i = 0; i < table.rows(); i++) {
            for (int j = 0; j < table.cols(); j++) {
                table.setValue(i, j, value++);
            }
        }
        
        System.out.println("Table contents:");
        System.out.println(table.toString());
        
        System.out.println("Rows: " + table.rows());
        System.out.println("Cols: " + table.cols());
        System.out.println("Value at (1,2): " + table.getValue(1, 2));
        System.out.println("Average: " + table.average());
        
        // Меняем значение
        table.setValue(0, 0, 100);
        System.out.println("\nAfter modification:");
        System.out.println(table.toString());
        System.out.println("New average: " + table.average());
    }
}