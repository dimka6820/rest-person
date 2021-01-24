package com.qa.rest.demo.repo;

import com.qa.rest.demo.entity.Person;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Repository
@Data
public class Repository {
    private int id = 0;
    private List<Person> personList = new ArrayList<>();

    public List<Person> findAllPerson() {
        return personList;
    }

    public Person findPersonById(int id) {
        return personList.stream()
                .filter(person -> person.getId() == id)
                .findFirst().orElse(null);
    }

    public int savePerson(Person person) {
        person.setId(++id);
        personList.add(person);

        return id;
    }

    public void deletePerson(int id) {
        personList.stream()
                .filter(person -> person.getId() == id)
                .findFirst()
                .ifPresent(personList::remove);
    }
}
