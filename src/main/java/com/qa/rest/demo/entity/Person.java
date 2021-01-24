package com.qa.rest.demo.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Person {
    private int id;
    private String name;
    private int age;
}
