package com.example;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Task3ProgressBarApp extends Application {

    private ProgressBar progressBar;
    private Button startButton;
    private Button pauseButton;
    private Button stopButton;
    private ProgressTask progressTask;
    private Thread workerThread;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("ProgressBar with Thread Control");

        // Создаем элементы UI
        progressBar = new ProgressBar(0);
        progressBar.setPrefWidth(300);

        startButton = new Button("Старт");
        pauseButton = new Button("Пауза");
        stopButton = new Button("Стоп");

        pauseButton.setDisable(true);
        stopButton.setDisable(true);

        // Обработчики кнопок
        startButton.setOnAction(e -> startTask());
        pauseButton.setOnAction(e -> togglePause());
        stopButton.setOnAction(e -> stopTask());

        // Размещение элементов
        HBox buttonBox = new HBox(10, startButton, pauseButton, stopButton);
        VBox root = new VBox(20, progressBar, buttonBox);
        root.setPadding(new Insets(20));

        Scene scene = new Scene(root, 400, 150);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void startTask() {
        if (progressTask != null && progressTask.isAlive()) {
            progressTask.interruptTask();
        }

        progressTask = new ProgressTask(progressBar);
        workerThread = new Thread(progressTask);
        workerThread.setDaemon(true);
        workerThread.start();

        updateButtonStates(true, false, true);
    }

    private void togglePause() {
        if (progressTask != null) {
            if (progressTask.isPaused()) {
                progressTask.resumeTask();
                pauseButton.setText("Пауза");
            } else {
                progressTask.pauseTask();
                pauseButton.setText("Продолжить");
            }
        }
    }

    private void stopTask() {
        if (progressTask != null) {
            progressTask.interruptTask();
            progressBar.setProgress(0);
            updateButtonStates(false, true, true);
        }
    }

    private void updateButtonStates(boolean startDisabled, boolean pauseDisabled, boolean stopDisabled) {
        startButton.setDisable(startDisabled);
        pauseButton.setDisable(pauseDisabled);
        stopButton.setDisable(stopDisabled);
        
        if (!pauseDisabled) {
            pauseButton.setText("Пауза");
        }
    }

    @Override
    public void stop() {
        if (progressTask != null) {
            progressTask.interruptTask();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}