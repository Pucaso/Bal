package com.example;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Task2GUIThreading extends Application {

    private Label statusLabel;
    private Button blockingButton;
    private Button runnableButton;
    private Button threadButton;
    private volatile boolean running = false;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("GUI Threading Demo");

        statusLabel = new Label("Статус: Готов");
        blockingButton = new Button("Запустить блокирующий цикл");
        runnableButton = new Button("Запустить через Runnable");
        threadButton = new Button("Запустить через Thread");

        // a. Блокирующая версия
        blockingButton.setOnAction(e -> {
            statusLabel.setText("Статус: Блокирующий цикл запущен");
            blockingInfiniteLoop();
        });

        // b. Версия с Runnable
        runnableButton.setOnAction(e -> {
            if (!running) {
                running = true;
                statusLabel.setText("Статус: Runnable поток запущен");
                
                Runnable task = new Runnable() {
                    @Override
                    public void run() {
                        infiniteLoopInBackground("Runnable");
                    }
                };
                
                Thread backgroundThread = new Thread(task);
                backgroundThread.setDaemon(true);
                backgroundThread.start();
            }
        });

        // b. Версия с Thread
        threadButton.setOnAction(e -> {
            if (!running) {
                running = true;
                statusLabel.setText("Статус: Thread поток запущен");
                
                Thread backgroundThread = new Thread() {
                    @Override
                    public void run() {
                        infiniteLoopInBackground("Thread");
                    }
                };
                
                backgroundThread.setDaemon(true);
                backgroundThread.start();
            }
        });

        VBox root = new VBox(10, statusLabel, blockingButton, runnableButton, threadButton);
        root.setPadding(new Insets(20));

        Scene scene = new Scene(root, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Блокирующий бесконечный цикл (демонстрация зависания GUI)
    private void blockingInfiniteLoop() {
        int count = 0;
        while (true) {
            count++;
            // Этот цикл заблокирует JavaFX Application Thread
            if (count % 1000000 == 0) {
                System.out.println("Блокирующий цикл: " + count);
            }
            
            // Попытка обновить GUI из основного потока (не сработает из-за блокировки)
            if (count % 5000000 == 0) {
                Platform.runLater(() -> 
                    statusLabel.setText("Блокирующий цикл: " + count)
                );
            }
        }
    }

    // Бесконечный цикл в фоновом потоке
    private void infiniteLoopInBackground(String type) {
        Thread currentThread = Thread.currentThread();
        int count = 0;
        
        try {
            while (running && !currentThread.isInterrupted()) {
                count++;
                
                if (count % 1000000 == 0) {
                    final int currentCount = count;
                    Platform.runLater(() -> 
                        statusLabel.setText(type + " поток: " + currentCount)
                    );
                }
                
                // Небольшая пауза чтобы не перегружать систему
                Thread.sleep(1);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            running = false;
            Platform.runLater(() -> 
                statusLabel.setText(type + " поток завершен")
            );
        }
    }

    @Override
    public void stop() {
        running = false;
    }

    public static void main(String[] args) {
        launch(args);
    }
}