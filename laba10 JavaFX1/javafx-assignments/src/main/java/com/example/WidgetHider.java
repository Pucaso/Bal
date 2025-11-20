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

public class WidgetHider extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Widget Hider");

        // Создаем виджеты
        ProgressBar progressBar = new ProgressBar(0.5);
        progressBar.setPrefWidth(150);
        
        Slider slider = new Slider(0, 100, 50);
        slider.setPrefWidth(150);
        
        Rectangle rectangle = new Rectangle(100, 60, Color.BLUE);
        
        TextArea textArea = new TextArea("Это текстовое поле\nМожно вводить текст");
        textArea.setPrefSize(150, 80);

        // Создаем чекбоксы
        CheckBox progressCheck = new CheckBox("Progress Bar");
        progressCheck.setSelected(true);
        
        CheckBox sliderCheck = new CheckBox("Slider");
        sliderCheck.setSelected(true);
        
        CheckBox rectCheck = new CheckBox("Rectangle");
        rectCheck.setSelected(true);
        
        CheckBox textCheck = new CheckBox("Text Area");
        textCheck.setSelected(true);

        // Привязываем видимость виджетов к чекбоксам
        progressBar.visibleProperty().bind(progressCheck.selectedProperty());
        slider.visibleProperty().bind(sliderCheck.selectedProperty());
        rectangle.visibleProperty().bind(rectCheck.selectedProperty());
        textArea.visibleProperty().bind(textCheck.selectedProperty());

        // Размещаем элементы
        VBox widgets = new VBox(10, 
            new HBox(10, progressCheck, progressBar),
            new HBox(10, sliderCheck, slider),
            new HBox(10, rectCheck, rectangle),
            new HBox(10, textCheck, textArea)
        );
        widgets.setPadding(new Insets(20));

        Scene scene = new Scene(widgets, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}