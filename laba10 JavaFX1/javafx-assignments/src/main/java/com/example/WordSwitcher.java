package com.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class WordSwitcher extends Application {
    private boolean isToRight = true;
    private TextField leftField;
    private TextField rightField;
    private Button switchButton;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Word Switcher");

        // Создаем поля ввода
        leftField = new TextField();
        leftField.setPromptText("Введите текст здесь");
        leftField.setPrefWidth(150);
        
        rightField = new TextField();
        rightField.setPromptText("Текст появится здесь");
        rightField.setPrefWidth(150);
        rightField.setEditable(false);

        // Создаем кнопку со стрелкой
        switchButton = new Button("→");
        switchButton.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        switchButton.setOnAction(e -> switchText());

        // Размещаем элементы
        HBox fieldsBox = new HBox(10, leftField, switchButton, rightField);
        fieldsBox.setAlignment(Pos.CENTER);
        fieldsBox.setPadding(new Insets(20));

        VBox root = new VBox(fieldsBox);
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 500, 100);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void switchText() {
        if (isToRight) {
            // Перемещаем текст справа налево
            rightField.setText(leftField.getText());
            leftField.clear();
            switchButton.setText("←");
        } else {
            // Перемещаем текст слева направо
            leftField.setText(rightField.getText());
            rightField.clear();
            switchButton.setText("→");
        }
        isToRight = !isToRight;
    }

    public static void main(String[] args) {
        launch(args);
    }
}