package human;

import java.util.Comparator;

/**
 * Компаратор для сравнения людей только по фамилии
 * Сравнение производится без учета регистра
 */
public class HumanComparatorByLName implements Comparator<Human> {
    
    @Override
    public int compare(Human h1, Human h2) {
        if (h1 == h2) return 0;
        if (h1 == null) return -1;
        if (h2 == null) return 1;
        
        return h1.getLastName().compareToIgnoreCase(h2.getLastName());
    }
}