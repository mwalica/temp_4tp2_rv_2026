package ch.walica.a53_temp161126_4tp2_rv.model;

import java.util.concurrent.ThreadLocalRandom;

public class Person {
    private String name;
    private int age;

    public Person(String name) {
        this.name = name;
        this.age = ThreadLocalRandom.current().nextInt(18, 101);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
