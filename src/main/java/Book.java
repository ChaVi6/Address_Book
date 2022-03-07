import java.util.*;

public class Book {

    public static class Address {
        private final String street;
        private final int numHouse;
        private final int numFlat;

        public Address(String street, int numHouse, int numFlat) {
            this.street = street;
            this.numHouse = numHouse;
            this.numFlat = numFlat;
        }



        public String getStreet() {
            return this.street;
        }

        public int getNumHouse() {
            return this.numHouse;
        }

        public int getNumFlat() {
            return this.numFlat;
        }


        @Override
        public String toString() {
            return this.street + ", дом " + this.numHouse + ", кв. " + this.numFlat;
        }

        @Override
        public int hashCode() {
            return this.street.hashCode() + this.numHouse + this.numFlat;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj instanceof Address) {
                Address other = (Address) obj;
                return this.street.equals(other.street) && this.numHouse == other.numHouse && this.numFlat == other.numFlat;
            }
            return false;
        }
    }




    private final HashMap<String, Address> book;


    public Book() {
        this.book = new HashMap<>();
    }


    public boolean addPerson(String person, Address adr) {
        if (this.book.containsKey(person)) return false;
        this.book.put(person, adr);
        return true;
    }

    public boolean deletePerson(String person) {
        if (!this.book.containsKey(person) || this.book.isEmpty()) return false;
        this.book.remove(person);
        return true;
    }

    public boolean changeAddress(String person, Address adr) {
        if (!this.book.containsKey(person) || this.book.isEmpty()) return false;
        this.book.replace(person, adr);
        return true;
    }

    public Address getAddressByName(String person) {
        return this.book.get(person);
    }

    public HashSet<String> getPeopleByStreet(String street) {
        HashSet<String> result = new HashSet<>();
        for (String str: this.book.keySet()) {
            Address adr = getAddressByName(str);
            if (street.equals(adr.getStreet())) result.add(str);
        }
        return result;
    }

    public HashSet<String> getPeopleByHouse(String street, int house) {
        HashSet<String> result = new HashSet<String>();
        for (String s: this.book.keySet()) {
            Address adr = getAddressByName(s);
            if (street.equals(adr.getStreet()) && house == adr.getNumHouse()) result.add(s);
        }
        return result;
    }


    @Override
    public String toString() {
        return this.book.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Book) {
            Book other = (Book) obj;
            return this.book.equals(other.book);
        }
        return false;
    }
}