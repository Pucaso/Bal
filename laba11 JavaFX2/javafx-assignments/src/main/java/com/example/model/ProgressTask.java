package com.example.model;

import javafx.application.Platform;
import javafx.scene.control.ProgressBar;

public class ProgressTask implements Runnable {
    private final ProgressBar progressBar;
    private volatile boolean paused = false;
    private volatile boolean interrupted = false;
    private int currentIteration = 0;
    private final Object pauseLock = new Object();
    private final int totalIterations = 1000;

    public ProgressTask(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    @Override
    public void run() {
        try {
            for (currentIteration = 0; currentIteration < totalIterations && !interrupted; currentIteration++) {
                
                // Проверка паузы
                synchronized (pauseLock) {
                    while (paused && !interrupted) {
                        pauseLock.wait();
                    }
                }
                
                if (interrupted) {
                    break;
                }

                // Имитация работы
                Thread.sleep(20);

                // Обновление прогресса в UI потоке
                final double progress = (double) currentIteration / totalIterations;
                Platform.runLater(() -> progressBar.setProgress(progress));

                // Вывод в консоль для отладки
                if (currentIteration % 100 == 0) {
                    System.out.println("Прогресс: " + currentIteration + "/" + totalIterations);
                }
            }

            // Завершение задачи
            if (!interrupted) {
                Platform.runLater(() -> progressBar.setProgress(1.0));
                System.out.println("Задача завершена успешно");
            } else {
                Platform.runLater(() -> progressBar.setProgress(0));
                System.out.println("Задача прервана");
            }

        } catch (InterruptedException e) {
            System.out.println("Поток был прерван");
            Thread.currentThread().interrupt();
        }
    }

    public void pauseTask() {
        paused = true;
    }

    public void resumeTask() {
        synchronized (pauseLock) {
            paused = false;
            pauseLock.notifyAll();
        }
    }

    public void interruptTask() {
        interrupted = true;
        resumeTask(); // Разбудить поток если он на паузе
    }

    public boolean isPaused() {
        return paused;
    }

    public boolean isAlive() {
        return !interrupted && currentIteration < totalIterations;
    }

    public int getCurrentIteration() {
        return currentIteration;
    }
}