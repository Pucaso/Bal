package primes;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class PrimesGenerator implements Iterable<Integer> {
    private final int count;
    
    public PrimesGenerator(int count) {
        if (count <= 0) {
            throw new IllegalArgumentException("Количество должно быть положительным числом");
        }
        this.count = count;
    }
    
    // Проверка, является ли число простым
    private boolean isPrime(int number) {
        if (number < 2) return false;
        if (number == 2) return true;
        if (number % 2 == 0) return false;
        
        for (int i = 3; i * i <= number; i += 2) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public Iterator<Integer> iterator() {
        return new PrimesIterator();
    }
    
    // Внутренний класс-итератор
    private class PrimesIterator implements Iterator<Integer> {
        private int generated = 0;
        private int current = 2;
        
        @Override
        public boolean hasNext() {
            return generated < count;
        }
        
        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Больше нет простых чисел");
            }
            
            // Ищем следующее простое число
            while (!isPrime(current)) {
                current++;
            }
            
            int prime = current;
            current++;
            generated++;
            return prime;
        }
    }
    
    // Метод для получения всех простых чисел в виде списка
    public java.util.List<Integer> getPrimesList() {
        java.util.List<Integer> primes = new java.util.ArrayList<>();
        for (int prime : this) {
            primes.add(prime);
        }
        return primes;
    }
}