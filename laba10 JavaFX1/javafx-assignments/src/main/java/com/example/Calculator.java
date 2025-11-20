package com.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Calculator extends Application {

    private TextField display;
    private double firstNumber = 0;
    private String operator = "";
    private boolean startNewNumber = true;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Calculator");
        primaryStage.setResizable(false);

        display = new TextField();
        display.setEditable(false);
        display.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        display.setPrefHeight(60);

        // Создаем кнопки
        String[][] buttonLabels = {
            {"7", "8", "9", "/"},
            {"4", "5", "6", "*"},
            {"1", "2", "3", "-"},
            {"0", ".", "=", "+"}
        };

        GridPane buttonGrid = new GridPane();
        buttonGrid.setHgap(5);
        buttonGrid.setVgap(5);
        buttonGrid.setPadding(new Insets(10));

        for (int row = 0; row < buttonLabels.length; row++) {
            for (int col = 0; col < buttonLabels[row].length; col++) {
                Button button = createButton(buttonLabels[row][col]);
                buttonGrid.add(button, col, row);
            }
        }

        // Кнопки очистки
        Button clearButton = createButton("C");
        Button clearAllButton = createButton("CE");

        GridPane clearGrid = new GridPane();
        clearGrid.setHgap(5);
        clearGrid.setVgap(5);
        clearGrid.setPadding(new Insets(10));
        clearGrid.add(clearButton, 0, 0);
        clearGrid.add(clearAllButton, 1, 0);

        VBox root = new VBox(10, display, buttonGrid, clearGrid);
        root.setPadding(new Insets(10));

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Button createButton(String text) {
        Button button = new Button(text);
        button.setPrefSize(60, 60);
        button.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        
        button.setOnAction(e -> handleButtonClick(text));
        
        return button;
    }

    private void handleButtonClick(String value) {
        if (value.matches("[0-9]")) {
            // Цифры
            if (startNewNumber) {
                display.setText(value);
                startNewNumber = false;
            } else {
                display.setText(display.getText() + value);
            }
        } else if (value.equals(".")) {
            // Точка
            if (startNewNumber) {
                display.setText("0.");
                startNewNumber = false;
            } else if (!display.getText().contains(".")) {
                display.setText(display.getText() + ".");
            }
        } else if (value.matches("[+\\-*/]")) {
            // Операторы
            if (!display.getText().isEmpty()) {
                firstNumber = Double.parseDouble(display.getText());
                operator = value;
                startNewNumber = true;
            }
        } else if (value.equals("=")) {
            // Равно
            if (!operator.isEmpty() && !display.getText().isEmpty()) {
                double secondNumber = Double.parseDouble(display.getText());
                double result = calculate(firstNumber, secondNumber, operator);
                display.setText(formatResult(result));
                operator = "";
                startNewNumber = true;
            }
        } else if (value.equals("C")) {
            // Очистка
            if (!display.getText().isEmpty()) {
                display.setText(display.getText().substring(0, display.getText().length() - 1));
                if (display.getText().isEmpty()) {
                    startNewNumber = true;
                }
            }
        } else if (value.equals("CE")) {
            // Полная очистка
            display.clear();
            firstNumber = 0;
            operator = "";
            startNewNumber = true;
        }
    }

    private double calculate(double first, double second, String op) {
        switch (op) {
            case "+": return first + second;
            case "-": return first - second;
            case "*": return first * second;
            case "/": 
                if (second == 0) {
                    display.setText("Error: Division by zero!");
                    startNewNumber = true;
                    return 0;
                }
                return first / second;
            default: return 0;
        }
    }

    private String formatResult(double result) {
        if (result == (long) result) {
            return String.format("%d", (long) result);
        } else {
            return String.format("%s", result);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}