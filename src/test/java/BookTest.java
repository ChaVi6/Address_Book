import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


class BookTest {

    Book a = new Book();
    Book b = new Book();
    Book.Address adr1 = new Book.Address("Невский пр.", 10, 100);
    Book.Address adr2 = new Book.Address("Приморский пр.", 25, 10);
    Book.Address adr3 = new Book.Address("Невский пр.", 15, 10);

    @Test
    void addPerson() {
        a.addPerson("Чаплин", adr1);
        b.addPerson("Чаплин", adr1);
        assertEquals(a, b);
        assertFalse(a.addPerson("Чаплин", adr1));
        a.addPerson("Чаплин", adr2);
        assertFalse(a.addPerson("Чаплин", adr2));
    }

    @Test
    void deletePerson() {
        a.addPerson("Чаплин", adr1);
        a.deletePerson("Чаплин");
        assertEquals(new Book(), a);
        assertFalse(a.deletePerson("Чаплин"));
        a.deletePerson("Чаплин");
        assertFalse(a.deletePerson("Чаплин"));
    }

    @Test
    void changeAddress() {
        a.addPerson("Чаплин", adr1);
        b.addPerson("Чаплин", adr2);
        b.changeAddress("Чаплин", adr1);
        assertEquals(a, b);
        b.deletePerson("Чаплин");
        assertFalse(b.changeAddress("Чаплин", adr2));
    }

    @Test
    void getAddressByName() {
        a.addPerson("Чаплин", adr1);
        assertEquals(adr1, a.getAddressByName("Чаплин"));
        assertNull(a.getAddressByName("Сергиенко"));
    }

    @Test
    void getPeopleByStreet() {
        a.addPerson("Чаплин", adr1);
        a.addPerson("Сергиенко", adr1);
        a.addPerson("Жилкина", adr1);
        a.addPerson("Ким", adr3);
        Set<String> residents = new HashSet<>();
        residents.add("Чаплин");
        residents.add("Сергиенко");
        residents.add("Жилкина");
        residents.add("Ким");
        assertEquals(residents, a.getPeopleByStreet("Невский пр."));
    }

    @Test
    void getPeopleByHouse() {
        a.addPerson("Чаплин", adr1);
        a.addPerson("Сергиенко", adr1);
        a.addPerson("Жилкина", adr1);
        a.addPerson("Ким", adr3);
        Set<String> residents = new HashSet<>();
        residents.add("Чаплин");
        residents.add("Сергиенко");
        residents.add("Жилкина");
        assertEquals(residents, a.getPeopleByHouse("Невский пр.", 10));
    }
}


