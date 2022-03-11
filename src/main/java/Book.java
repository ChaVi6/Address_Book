import java.util.*;

public class Book {

    public static class Address {
        private final String street;
        private final int numHouse;
        private final int numFlat;

        public Address(String street, Integer numHouse, Integer numFlat) {
            this.street = Objects.requireNonNull(street, "The address is incorrect. Street can't be null");
            this.numHouse = Objects.requireNonNull(numHouse, "The address is incorrect. House number can't be null");
            this.numFlat = Objects.requireNonNull(numFlat, "The address is incorrect. Flat number can't be null");
        }

        private void checkException(int numHouse, int numFlat) {
            if ((numHouse <= 0) || (numFlat <= 0))  {
                throw new IllegalArgumentException("The address is incorrect");
            }
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
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Address address = (Address) o;
            return numHouse == address.numHouse && numFlat == address.numFlat && Objects.equals(street, address.street);
        }

        @Override
        public int hashCode() {
            return Objects.hash(street, numHouse, numFlat);
        }
    }



        private final Map<String, Address> book;

        public Book(Map<String, Address> book) {
            this.book = book;
        }


        public boolean addPerson(String person, Address adr) {
            if (book.containsKey(person)) return false;
            book.put(person, adr);
            return true;
        }

        public boolean deletePerson(String person) {
            if (!book.containsKey(person)) return false;
            book.remove(person);
            return true;
        }

        public boolean changeAddress(String person, Address adr) {
            if (!this.book.containsKey(person)) return false;
            this.book.replace(person, adr);
            return true;
        }

        public Address getAddressByName(String person) {
            if (this.book.containsKey(person)) return book.get(person);
            return null;
        }

        public Set<String> getPeopleByStreet(Address street) {
            Set<String> result = new HashSet<>();
            for (String str: this.book.keySet()) {
                if (book.get(str).getStreet().equals(street.getStreet())) result.add(str);
            }
            return result;
        }

        public Set<String> getPeopleByHouse(Address house) {
            Set<String> result = new HashSet<>();
            for (String str: this.book.keySet()) {
                if (book.get(str).street.equals(house.getStreet()) && book.get(str).getNumHouse() == house.getNumHouse()) result.add(str);
            }
            return result;
        }


        @Override
        public String toString() {
            return this.book.toString();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Book book1 = (Book) o;
            return Objects.equals(book, book1.book);
        }

        @Override
        public int hashCode() {
            return Objects.hash(book);
        }
}

