package com.kunev.models;

import java.math.BigDecimal;

public class Person {
    private final String name;
    private final BigDecimal spending;

    public Person(String name, BigDecimal spending) {
        this.name = name;
        this.spending = spending;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getSpending() {
        return spending;
    }

    @Override
    public String toString() {
        return name;
    }
}
