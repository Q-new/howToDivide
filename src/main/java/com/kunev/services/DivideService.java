package com.kunev.services;

import com.kunev.models.Creditor;
import com.kunev.models.Debtor;
import com.kunev.models.Person;
import com.kunev.models.Repayment;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DivideService {
    private static List<Person> persons = new ArrayList<>();
    private static List<Debtor> debtors = new ArrayList<>();
    private static List<Creditor> creditors = new ArrayList<>();
    private static BigDecimal average = new BigDecimal(0);
    private static final List<Repayment> repayments = new ArrayList<>();

    public static void addPerson(Person person) {
        persons.add(person);
    }

    public static BigDecimal getAverage() {
        return average;
    }

    public static void clear() {
        persons = new ArrayList<>();
        debtors = new ArrayList<>();
        creditors = new ArrayList<>();
    }

    public static List<Repayment> divide() {
        if (persons.size() == 0) {
            throw new RuntimeException("Нет не одной персоны");
        }
        if (persons.size() == 1) {
            throw new RuntimeException("Нельзя сделать расчёт для одного человека");
        }
        average = BigDecimal.valueOf(persons.stream()
                .mapToDouble(p -> p.getSpending().doubleValue())
                .average()
                .getAsDouble());
        debtors = getDebtors();
        creditors = getCreditors();

        fillRepayments();

        return repayments;
    }

    private static void fillRepayments() {
        for (Creditor creditor : creditors) {
            while (creditor.getCredit().doubleValue() > 0.01) {
                minusCredit(creditor, debtors.remove(0));
            }
        }
    }

    private static void minusCredit(Creditor creditor, Debtor debtor) {
        if(creditor.getCredit().compareTo(debtor.getDuty()) > 0){
            repayments.add(new Repayment(debtor, creditor, debtor.getDuty()));
            creditor.setCredit(creditor.getCredit().subtract(debtor.getDuty()));
        } else if (creditor.getCredit().compareTo(debtor.getDuty()) == 0){
            creditor.setCredit(BigDecimal.valueOf(0));
        } else {
            repayments.add(new Repayment(debtor, creditor, creditor.getCredit()));
            debtor.setDuty(debtor.getDuty().subtract(creditor.getCredit()));
            creditor.setCredit(BigDecimal.valueOf(0));
            debtors.add(debtor);
        }
    }

    private static List<Creditor> getCreditors() {
        return persons.stream()
                .filter(person -> (person.getSpending().compareTo(average)) > 0)
                .map(person -> new Creditor(person, person.getSpending().subtract(average)))
                .sorted()
                .collect(Collectors.toList());
    }

    private static List<Debtor> getDebtors() {
        return persons.stream()
                .filter(person -> (person.getSpending().compareTo(average)) < 0)
                .map(person -> new Debtor(person, average.subtract(person.getSpending())))
                .sorted()
                .collect(Collectors.toList());
    }


}
