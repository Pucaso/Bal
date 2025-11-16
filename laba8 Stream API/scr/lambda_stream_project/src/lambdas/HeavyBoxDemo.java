package lambdas;

import java.util.function.Consumer;

class HeavyBox {
    private int weight;
    
    public HeavyBox(int weight) {
        this.weight = weight;
    }
    
    public int getWeight() {
        return weight;
    }
    
    @Override
    public String toString() {
        return "HeavyBox{weight=" + weight + "}";
    }
}

public class HeavyBoxDemo {
    public static void main(String[] args) {
        // Consumer для отгрузки
        Consumer<HeavyBox> shipBox = box -> 
            System.out.println("Отгрузили ящик с весом " + box.getWeight());
        
        // Consumer для отправки
        Consumer<HeavyBox> sendBox = box -> 
            System.out.println("Отправляем ящик с весом " + box.getWeight());
        
        // Комбинированный Consumer с andThen
        Consumer<HeavyBox> shipAndSend = shipBox.andThen(sendBox);
        
        // Тестирование
        HeavyBox box1 = new HeavyBox(50);
        HeavyBox box2 = new HeavyBox(100);
        
        System.out.println("=== Обработка HeavyBox ===");
        System.out.println("Box 1:");
        shipAndSend.accept(box1);
        
        System.out.println("\nBox 2:");
        shipBox.andThen(sendBox).accept(box2);
    }
}