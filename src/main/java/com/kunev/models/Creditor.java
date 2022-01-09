package com.kunev.models;

import java.math.BigDecimal;

public class Creditor extends Person implements Comparable{
    private BigDecimal credit;

    public Creditor(String name, BigDecimal spending) {
        super(name, spending);
        this.credit = BigDecimal.valueOf(0);
    }

    public Creditor(Person person, BigDecimal credit){
        super(person.getName(), person.getSpending());
        this.credit = credit;
    }

    public BigDecimal getCredit() {
        return credit;
    }

    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public int compareTo(Object o) {
        return credit.compareTo(((Creditor) o).getCredit());
    }
}
