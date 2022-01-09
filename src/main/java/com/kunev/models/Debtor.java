package com.kunev.models;

import java.math.BigDecimal;

public class Debtor extends Person implements Comparable{
    private BigDecimal duty;

    public Debtor(String name, BigDecimal spending) {
        super(name, spending);
        this.duty = BigDecimal.valueOf(0);
    }

    public Debtor(Person person, BigDecimal duty) {
        super(person.getName(), person.getSpending());
        this.duty = duty;
    }

    public BigDecimal getDuty() {
        return duty;
    }

    public void setDuty(BigDecimal duty) {
        this.duty = duty;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public int compareTo(Object o) {
        return duty.compareTo(((Debtor)o).getDuty());
    }
}
