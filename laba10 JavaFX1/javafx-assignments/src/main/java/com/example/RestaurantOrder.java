package com.example;

import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RestaurantOrder extends Application {

    private TableView<Dish> table;
    private ObservableList<Dish> dishes;
    private Label totalLabel;
    private SimpleDoubleProperty totalCost = new SimpleDoubleProperty(0);

    public static class Dish {
        private final SimpleStringProperty name;
        private final SimpleDoubleProperty price;
        private final SimpleIntegerProperty quantity;
        private final SimpleDoubleProperty total;

        public Dish(String name, double price) {
            this.name = new SimpleStringProperty(name);
            this.price = new SimpleDoubleProperty(price);
            this.quantity = new SimpleIntegerProperty(0);
            this.total = new SimpleDoubleProperty(0);
        }

        // Getters and setters
        public String getName() { return name.get(); }
        public double getPrice() { return price.get(); }
        public int getQuantity() { return quantity.get(); }
        public double getTotal() { return total.get(); }
        
        public void setQuantity(int quantity) { 
            this.quantity.set(quantity); 
            this.total.set(quantity * getPrice());
        }
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Restaurant Order");

        // Инициализация списка блюд
        dishes = FXCollections.observableArrayList(
            new Dish("Паста Карбонара", 12.99),
            new Dish("Пицца Маргарита", 10.99),
            new Dish("Салат Цезарь", 8.99),
            new Dish("Стейк", 18.99),
            new Dish("Суп Том Ям", 9.99)
        );

        // Создаем таблицу для чека
        table = new TableView<>();
        
        TableColumn<Dish, String> nameCol = new TableColumn<>("Блюдо");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        TableColumn<Dish, Double> priceCol = new TableColumn<>("Цена");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        TableColumn<Dish, Integer> quantityCol = new TableColumn<>("Количество");
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        
        TableColumn<Dish, Double> totalCol = new TableColumn<>("Итого");
        totalCol.setCellValueFactory(new PropertyValueFactory<>("total"));

        table.getColumns().addAll(nameCol, priceCol, quantityCol, totalCol);
        table.setItems(dishes);

        // Создаем элементы управления для каждого блюда
        VBox dishControls = new VBox(10);
        for (Dish dish : dishes) {
            HBox control = createDishControl(dish);
            dishControls.getChildren().add(control);
        }

        // Панель итогов
        totalLabel = new Label();
        totalLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        updateTotal();

        // Привязываем обновление общей суммы
        totalCost.addListener((obs, oldVal, newVal) -> {
            totalLabel.setText(String.format("Общая стоимость: $%.2f", newVal));
        });

        VBox root = new VBox(10, 
            new Label("Выберите блюда:"), 
            dishControls, 
            new Separator(),
            table,
            totalLabel
        );
        root.setPadding(new Insets(20));

        Scene scene = new Scene(root, 600, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private HBox createDishControl(Dish dish) {
        CheckBox checkBox = new CheckBox(dish.getName() + " - $" + dish.getPrice());
        
        Spinner<Integer> quantitySpinner = new Spinner<>(0, 10, 0);
        quantitySpinner.setEditable(true);
        quantitySpinner.setVisible(false);
        
        Label dishTotal = new Label("$0.00");
        dishTotal.setVisible(false);

        checkBox.selectedProperty().addListener((obs, oldVal, newVal) -> {
            quantitySpinner.setVisible(newVal);
            dishTotal.setVisible(newVal);
            if (newVal) {
                quantitySpinner.getValueFactory().setValue(1);
            } else {
                quantitySpinner.getValueFactory().setValue(0);
            }
        });

        quantitySpinner.valueProperty().addListener((obs, oldVal, newVal) -> {
            dish.setQuantity(newVal);
            dishTotal.setText(String.format("$%.2f", dish.getTotal()));
            updateTotal();
        });

        return new HBox(10, checkBox, quantitySpinner, dishTotal);
    }

    private void updateTotal() {
        double total = dishes.stream()
                .mapToDouble(Dish::getTotal)
                .sum();
        totalCost.set(total);
    }

    public static void main(String[] args) {
        launch(args);
    }
}