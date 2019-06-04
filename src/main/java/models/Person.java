package models;

import java.util.Objects;

/**
 * Angajati
 * Structuri ierarhice
 * Salarii
 */

public abstract class Person {
    private String firsName;
    private String lastName;
    private int age;

    public Person() {
    }

    public Person(String firsName, String lastName, int age) {
        this.firsName = firsName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getName() {
        StringBuilder temporaryLastName = new StringBuilder(this.lastName);
        temporaryLastName.append(" ").append(this.firsName);
        return String.valueOf(temporaryLastName);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return getAge() == person.getAge() &&
                Objects.equals(firsName, person.firsName) &&
                Objects.equals(lastName, person.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firsName, lastName, getAge());
    }
}
