import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


class BookTest {

    Book book = new Book(new HashMap<>());

    @BeforeEach
    void setUp() {
        assertTrue(book.addPerson("Чаплин", new Book.Address("Невский пр.", 6, 20)));
        assertTrue(book.addPerson("Сергиенко", new Book.Address("Приморский пр.", 5, 50)));
        assertTrue(book.addPerson("Жилкина", new Book.Address("Невский пр.", 10, 5)));
    }

    @Test
    void addPerson() {
        assertFalse(book.addPerson("Сергиенко", new Book.Address("Приморский пр.", 5, 150)));
    }

    @Test
    void deletePerson() {
        assertTrue(book.deletePerson("Сергиенко"));
        assertTrue(book.deletePerson("Жилкина"));
        assertFalse(book.deletePerson("Шайхайдаров"));
    }

    @Test
    void changeAddress() {
        assertTrue(book.deletePerson("Сергиенко"));
        assertTrue(book.changeAddress("Чаплин", new Book.Address("Невский", 10, 5)));
        assertTrue(book.changeAddress("Жилкина", new Book.Address("Невский пр.", 6, 20)));
        assertFalse(book.changeAddress("Сергиенко", new Book.Address("Невский пр.", 6, 20)));
    }

    @Test
    void getAddressByName() {
        assertEquals(new Book.Address("Невский пр.", 6, 20), book.getAddressByName("Чаплин"));
        assertEquals(new Book.Address("Приморский пр.", 5, 50), book.getAddressByName("Сергиенко"));
        assertEquals(new Book.Address("Невский пр.", 10, 5), book.getAddressByName("Жилкина"));
        assertNull(book.getAddressByName("Белова"));
    }

    @Test
    void getPeopleByStreet() {
        assertTrue(book.addPerson("Ким", new Book.Address("Приморский пр.", 10, 100)));
        assertTrue(book.addPerson("Ли", new Book.Address("Невский пр.", 6, 20)));
        assertTrue(book.addPerson("Шайхайдаров", new Book.Address("Приморский пр.", 5, 50)));
        assertFalse(book.addPerson("Ким", new Book.Address("Площадь Восстания", 27, 3)));
        assertFalse(book.addPerson("Чаплин", new Book.Address("Невский пр.", 27, 3)));
        assertTrue(book.deletePerson("Ли"));
        assertFalse(book.deletePerson("Чадлин"));
        assertTrue(book.changeAddress("Шайхайдаров", new Book.Address("Площадь Восстания", 27, 3)));
        assertTrue(book.changeAddress("Сергиенко", new Book.Address("Невский пр.", 27, 3)));
        assertFalse(book.changeAddress("Ли", new Book.Address("Невский", 10, 5)));
        Book.Address adr = new Book.Address("Невский пр.", 10, 100);
        Set<String> residents = Set.of("Чаплин", "Сергиенко", "Жилкина");
        assertEquals(residents, book.getPeopleByStreet(adr));
    }

    @Test
    void getPeopleByHouse() {
        assertTrue(book.addPerson("Ким", new Book.Address("Приморский пр.", 10, 100)));
        assertTrue(book.addPerson("Ли", new Book.Address("Невский пр.", 6, 21)));
        assertTrue(book.addPerson("Шайхайдаров", new Book.Address("Приморский пр.", 5, 50)));
        assertFalse(book.addPerson("Ким", new Book.Address("Невский пр.", 6, 20)));
        assertTrue(book.deletePerson("Ким"));
        assertFalse(book.deletePerson("Сиргиенко"));
        assertTrue(book.changeAddress("Шайхайдаров", new Book.Address("Невский пр.", 6, 20)));
        assertTrue(book.changeAddress("Сергиенко", new Book.Address("Невский пр.", 7, 20)));
        assertFalse(book.changeAddress("Ким", new Book.Address("Невский пр.", 6, 21)));
        Book.Address str = new Book.Address("Невский пр.", 6, 100);
        Set<String> residents = Set.of("Чаплин", "Шайхайдаров", "Ли");
        assertEquals(residents, book.getPeopleByHouse(str));
    }
}

