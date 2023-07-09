package com.ascending.demo.api.dto;

public class Person {
    private String name;
    private String title;
    private int age;

    private Person(PersonBuilder personBuilder) {
        this.name = personBuilder.name;
        this.title = personBuilder.title;
        this.age = personBuilder.age;
    }

    //1. 先build一个builder class 2.对每一个方法写return自己 3.最后return外面的class
    public static class PersonBuilder {
        private String name;
        private String title;
        private int age;

        public PersonBuilder name (String name) {
            this.name = name;
            return this; //return的是personBuilder
        }

        public PersonBuilder title (String title) {
            this.title = title;
            return this;
        }

        public PersonBuilder age (int age) {
            this.age = age;
            return this;
        }

        public Person build() {
            return new Person(this);
        }
    }
}
