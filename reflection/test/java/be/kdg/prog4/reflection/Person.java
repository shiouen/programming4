package be.kdg.prog4.reflection;

import be.kdg.prog4.reflection.annotations.Id;
import be.kdg.prog4.reflection.annotations.Storable;

@Storable(table="PERSON")
public class Person {
    @Id
    private String naam;
    private String interesse;
    private int leeftijd;

    public Person() {
        this.naam = "";
        this.interesse = "";
        this.leeftijd = 0;
    }

    public Person(String naam, String interesse, int leeftijd) {
        this.naam = naam;
        this.interesse = interesse;
        this.leeftijd = leeftijd;
    }

    public String toString() {
        return naam + ", " + leeftijd + ": " + interesse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (leeftijd != person.leeftijd) return false;
        if (interesse != null ? !interesse.equals(person.interesse) : person.interesse != null) return false;
        if (naam != null ? !naam.equals(person.naam) : person.naam != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = naam != null ? naam.hashCode() : 0;
        result = 31 * result + (interesse != null ? interesse.hashCode() : 0);
        result = 31 * result + leeftijd;
        return result;
    }
}
