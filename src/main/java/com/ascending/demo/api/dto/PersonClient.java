package com.ascending.demo.api.dto;

public class PersonClient {
    public Person createPerson() {
        Person person = new Person.PersonBuilder().name("Jingrong").title("Student").build();
        return person;
    }

    public Person createSecondPerson() {
        Person person = new Person.PersonBuilder().name("Frank").title("Student").age(15).build();
        return person;
    }
}
