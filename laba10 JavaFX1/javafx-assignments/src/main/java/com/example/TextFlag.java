package com.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class TextFlag extends Application {

    private ToggleGroup stripe1Group = new ToggleGroup();
    private ToggleGroup stripe2Group = new ToggleGroup();
    private ToggleGroup stripe3Group = new ToggleGroup();
    
    private String[] colors = {"Красный", "Синий", "Зеленый", "Желтый", "Белый"};
    private Color[] colorValues = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.WHITE};

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Text Flag");
        primaryStage.setResizable(false);

        // Создаем панели выбора цвета для каждой полосы
        VBox stripe1Panel = createStripePanel("Первая полоса:", stripe1Group);
        VBox stripe2Panel = createStripePanel("Вторая полоса:", stripe2Group);
        VBox stripe3Panel = createStripePanel("Третья полоса:", stripe3Group);

        // Визуальное представление флага
        Rectangle stripe1 = new Rectangle(200, 30);
        Rectangle stripe2 = new Rectangle(200, 30);
        Rectangle stripe3 = new Rectangle(200, 30);
        
        VBox flagPreview = new VBox(stripe1, stripe2, stripe3);
        flagPreview.setStyle("-fx-border-color: black; -fx-border-width: 1px;");

        // Кнопка и результат
        Button drawButton = new Button("Нарисовать");
        Label resultLabel = new Label();
        resultLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        drawButton.setOnAction(e -> {
            String color1 = getSelectedColor(stripe1Group);
            String color2 = getSelectedColor(stripe2Group);
            String color3 = getSelectedColor(stripe3Group);
            
            if (color1 != null && color2 != null && color3 != null) {
                resultLabel.setText(color1 + ", " + color2 + ", " + color3);
                
                // Обновляем визуальное представление
                updateFlagColors(stripe1, stripe2, stripe3, color1, color2, color3);
            } else {
                resultLabel.setText("Пожалуйста, выберите цвета для всех полос!");
            }
        });

        HBox stripesSelection = new HBox(20, stripe1Panel, stripe2Panel, stripe3Panel);
        VBox root = new VBox(20, stripesSelection, flagPreview, drawButton, resultLabel);
        root.setPadding(new Insets(20));

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createStripePanel(String title, ToggleGroup group) {
        Label titleLabel = new Label(title);
        VBox panel = new VBox(5, titleLabel);
        
        for (int i = 0; i < colors.length; i++) {
            RadioButton radio = new RadioButton(colors[i]);
            radio.setToggleGroup(group);
            panel.getChildren().add(radio);
        }
        
        panel.setPadding(new Insets(10));
        panel.setStyle("-fx-border-color: lightgray; -fx-border-width: 1px;");
        return panel;
    }

    private String getSelectedColor(ToggleGroup group) {
        RadioButton selected = (RadioButton) group.getSelectedToggle();
        return selected != null ? selected.getText() : null;
    }

    private void updateFlagColors(Rectangle stripe1, Rectangle stripe2, Rectangle stripe3, 
                                 String color1, String color2, String color3) {
        stripe1.setFill(getColorValue(color1));
        stripe2.setFill(getColorValue(color2));
        stripe3.setFill(getColorValue(color3));
    }

    private Color getColorValue(String colorName) {
        for (int i = 0; i < colors.length; i++) {
            if (colors[i].equals(colorName)) {
                return colorValues[i];
            }
        }
        return Color.WHITE;
    }

    public static void main(String[] args) {
        launch(args);
    }
}