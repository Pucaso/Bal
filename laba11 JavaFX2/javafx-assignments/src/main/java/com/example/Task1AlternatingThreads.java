package com.example;

public class Task1AlternatingThreads {
    private static final Object lock = new Object();
    private static boolean thread1Turn = true;

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        synchronized (lock) {
                            while (!thread1Turn) {
                                lock.wait();
                            }
                            System.out.println("Поток1");
                            thread1Turn = false;
                            lock.notifyAll();
                            Thread.sleep(500); // Задержка для читаемости вывода
                        }
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        synchronized (lock) {
                            while (thread1Turn) {
                                lock.wait();
                            }
                            System.out.println("Поток2");
                            thread1Turn = true;
                            lock.notifyAll();
                            Thread.sleep(500); // Задержка для читаемости вывода
                        }
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        thread1.setName("Thread-1");
        thread2.setName("Thread-2");

        thread1.start();
        thread2.start();

        // Остановка через 10 секунд для демонстрации
        try {
            Thread.sleep(10000);
            thread1.interrupt();
            thread2.interrupt();
            System.out.println("Программа завершена.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}