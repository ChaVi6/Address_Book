import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


class BookTest {

    @Test
    void addPerson() {
        Book book = new Book(new HashMap<>());
        book.addPerson("Чаплин", new Book.Address("Невский пр.", 6, 20));
        book.addPerson("Сергиенко", new Book.Address("Приморский пр.", 5, 50));
        Map<String, Book.Address> addresses = new HashMap<>();
        addresses.put("Чаплин", new Book.Address("Невский пр.", 6, 20));
        addresses.put("Сергиенко", new Book.Address("Приморский пр.", 5, 50));
        assertEquals(book, new Book(addresses));
    }

    @Test
    void deletePerson() {
        Map<String, Book.Address> adr = new HashMap<>();
        adr.put("Чаплин", new Book.Address("Невский пр.", 6, 20));
        adr.put("Сергиенко", new Book.Address("Приморский пр.", 5, 50));
        Book book = new Book(adr);
        book.deletePerson("Чаплин");
        book.deletePerson("Сергиенко");
        Map<String, Book.Address> result = new HashMap<>();
        assertEquals(book, new Book(result));
    }

    @Test
    void changeAddress() {
        Map<String, Book.Address> adr1 = new HashMap<>();
        adr1.put("Чаплин", new Book.Address("Невский пр.", 6, 20));
        Book book1 = new Book(adr1);
        book1.changeAddress("Чаплин", new Book.Address("Школьная ул.", 15, 631));
        Map<String, Book.Address> result1 = new HashMap<>();
        result1.put("Чаплин", new Book.Address("Школьная ул.", 15, 631));
        assertEquals(book1, new Book(result1));

        Map<String, Book.Address> adr2 = new HashMap<>();
        adr2.put("Сергиенко", new Book.Address("Приморский пр.", 5, 50));
        Book book2 = new Book(adr2);
        book2.changeAddress("Сергиенко", new Book.Address("наб. реки Фонтанки", 3, 1));
        Map<String, Book.Address> result2 = new HashMap<>();
        result2.put("Сергиенко", new Book.Address("наб. реки Фонтанки", 3, 1));
        assertEquals(book2, new Book(result2));
    }

    @Test
    void getAddressByName() {
        Map<String, Book.Address> adr = new HashMap<>();
        adr.put("Чаплин", new Book.Address("Невский пр.", 6, 20));
        adr.put("Сергиенко", new Book.Address("наб. реки Фонтанки", 3, 1));
        Book book = new Book(adr);
        assertEquals(book.getAddressByName("Чаплин"), new Book.Address("Невский пр.", 6, 20));
        assertEquals(book.getAddressByName("Сергиенко"), new Book.Address("наб. реки Фонтанки", 3, 1));
    }

    @Test
    void getPeopleByStreet() {
        Book book = new Book(new HashMap<>());
        book.addPerson("Чаплин", new Book.Address("Невский", 10, 100));
        book.addPerson("Сергиенко", new Book.Address("Невский", 30, 100));
        book.addPerson("Жилкина", new Book.Address("Невский", 10, 5));
        book.addPerson("Ким", new Book.Address("Приморский", 10, 100));
        book.addPerson("Ли", new Book.Address("Невский", 10, 100));
        book.addPerson("Шайхайдаров", new Book.Address("Невский", 10, 100));
        book.deletePerson("Ли");
        book.changeAddress("Шайхайдаров", new Book.Address("Площадь Восстания", 27, 3));
        book.changeAddress("Сергиенко", new Book.Address("Невский", 27, 3));
        Set<String> residents = new HashSet<>();
        residents.add("Чаплин");
        residents.add("Сергиенко");
        residents.add("Жилкина");
        assertEquals(residents, book.getPeopleByStreet(new Book.Address("Невский", 10, 100)));
    }

    @Test
    void getPeopleByHouse() {
        Book book = new Book(new HashMap<>());
        book.addPerson("Чаплин", new Book.Address("Невский", 10, 100));
        book.addPerson("Сергиенко", new Book.Address("Невский", 30, 100));
        book.addPerson("Жилкина", new Book.Address("Невский", 10, 5));
        book.addPerson("Ким", new Book.Address("Приморский", 10, 100));
        book.addPerson("Ли", new Book.Address("Невский", 10, 100));
        book.addPerson("Шайхайдаров", new Book.Address("Невский", 10, 100));
        book.deletePerson("Ким");
        book.deletePerson("Ли");
        book.changeAddress("Шайхайдаров", new Book.Address("Невский", 10, 99));
        book.changeAddress("Сергиенко", new Book.Address("Невский", 27, 3));
        Set<String> residents = new HashSet<>();
        residents.add("Чаплин");
        residents.add("Жилкина");
        residents.add("Шайхайдаров");
        assertEquals(residents, book.getPeopleByHouse(new Book.Address("Невский", 10, 100)));
    }
}


