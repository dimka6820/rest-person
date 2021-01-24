package com.qa.rest.demo.controller;

import com.qa.rest.demo.entity.Person;
import com.qa.rest.demo.repo.Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class Controller {
    private final Repository repository;

    @GetMapping("/")
    public ResponseEntity<String> hello() {
        return ResponseEntity
                .ok()
                .body("Hello world!");
    }

    @GetMapping("/find")
    public ResponseEntity<List<Person>> find() {
        return ResponseEntity
                .ok()
                .body(repository.findAllPerson());
    }

    @GetMapping("/findById")
    public ResponseEntity<Person> findById(@RequestParam(value = "id") Integer id) {
        return ResponseEntity
                .ok()
                .body(repository.findPersonById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestParam(value = "name") String name, @RequestParam(value = "age") Integer age) {
        if (age < 0 || age > 200) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("age should be between 0 and 200");
        }

        if ("".equalsIgnoreCase(name)) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("name can't be empty");
        }

        return ResponseEntity
                .ok()
                .body(
                        repository.savePerson(Person.builder()
                                .name(name)
                                .age(age)
                                .build())
                );
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam(value = "id") Integer id) {
        if (id == null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("id can't be empty");
        }
        repository.deletePerson(id);
        return ResponseEntity
                .ok()
                .body("Person was successfully deleted");
    }
}
