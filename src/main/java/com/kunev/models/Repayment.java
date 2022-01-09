package com.kunev.models;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Repayment {
    private Debtor debtor;
    private Creditor creditor;
    private BigDecimal credit;

    public Repayment(Debtor debtor, Creditor creditor, BigDecimal credit) {
        this.debtor = debtor;
        this.creditor = creditor;
        this.credit = credit.setScale(2, RoundingMode.HALF_EVEN);
    }

    @Override
    public String toString() {
        return String.format("%s должен вернуть %s сумму в размере %s р.", debtor, creditor, credit.setScale(2));
    }
}
