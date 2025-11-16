package demo;

import model.Client;
import model.Phone;
import model.PhoneType;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ClientDemo {
    public static void main(String[] args) {
        // Создание клиентов с телефонами
        List<Client> clients = Arrays.asList(
            createClient(1, "Иван Иванов", 25, 
                new Phone("+7-911-111-11-11", PhoneType.MOBILE),
                new Phone("+7-812-111-11-11", PhoneType.LANDLINE)),
            createClient(2, "Мария Петрова", 22, 
                new Phone("+7-911-222-22-22", PhoneType.MOBILE)),
            createClient(3, "Петр Сидоров", 30, 
                new Phone("+7-812-333-33-33", PhoneType.LANDLINE)),
            createClient(4, "Анна Козлова", 19, 
                new Phone("+7-911-444-44-44", PhoneType.MOBILE),
                new Phone("+7-911-555-55-55", PhoneType.MOBILE)),
            createClient(5, "Сергей Николаев", 28, 
                new Phone("+7-812-666-66-66", PhoneType.LANDLINE))
        );
        
        System.out.println("=== Все клиенты ===");
        clients.forEach(System.out::println);
        
        // Поиск самого молодого клиента с мобильным телефоном
        Optional<Client> youngestClientWithMobile = clients.stream()
            .filter(client -> client.getPhones().stream()
                .anyMatch(phone -> phone.getType() == PhoneType.MOBILE))
            .min((c1, c2) -> Integer.compare(c1.getAge(), c2.getAge()));
        
        System.out.println("\n=== Самый молодой клиент с мобильным телефоном ===");
        youngestClientWithMobile.ifPresentOrElse(
            client -> System.out.println("Найден: " + client),
            () -> System.out.println("Клиенты с мобильными телефонами не найдены")
        );
        
        // Дополнительная статистика
        System.out.println("\n=== Статистика по клиентам ===");
        long mobileUsers = clients.stream()
            .filter(client -> client.getPhones().stream()
                .anyMatch(phone -> phone.getType() == PhoneType.MOBILE))
            .count();
        
        long landlineUsers = clients.stream()
            .filter(client -> client.getPhones().stream()
                .anyMatch(phone -> phone.getType() == PhoneType.LANDLINE))
            .count();
            
        System.out.println("Всего клиентов: " + clients.size());
        System.out.println("С мобильными телефонами: " + mobileUsers);
        System.out.println("Со стационарными телефонами: " + landlineUsers);
    }
    
    private static Client createClient(int id, String name, int age, Phone... phones) {
        Client client = new Client(id, name, age);
        for (Phone phone : phones) {
            client.addPhone(phone);
        }
        return client;
    }
}