package com.proposalmanager.proposal;

import com.proposalmanager.credit.enums.CreditKind;
import com.proposalmanager.proposal.enums.DescriptionRejected;
import com.proposalmanager.proposal.enums.StatusProposal;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class Proposal {
    private final Long id;
    private final Long userId;
    private final Long accountId;
    private final double amountOfCredit;
    private final int month;
    private final double salary;
    private final double interest;
    private final double commission;
    private final double monthlyFee;
    private final String currency;
    private final String currencySymbol;
    private final String username;
    private final String purpose;
    private final String proposalNumber;
    private final LocalDate dateFrom;
    private final LocalDate dateTo;
    private final boolean acceptStatement;
    @Enumerated(EnumType.STRING)
    private final StatusProposal statusProposal;
    @Enumerated(EnumType.STRING)
    private final DescriptionRejected descriptionRejected;
    @Enumerated(EnumType.STRING)
    private final CreditKind creditKind;

    private Proposal(final BuilderProposal builder) {
        this.id = builder.id;
        this.userId = builder.userId;
        this.accountId = builder.accountId;
        this.amountOfCredit = builder.amountOfCredit;
        this.month = builder.month;
        this.salary = builder.salary;
        this.interest = builder.interest;
        this.commission = builder.commission;
        this.monthlyFee = builder.monthlyFee;
        this.currency = builder.currency;
        this.currencySymbol = builder.currencySymbol;
        this.username = builder.username;
        this.purpose = builder.purpose;
        this.proposalNumber = builder.proposalNumber;
        this.dateFrom = builder.dateFrom;
        this.dateTo = builder.dateTo;
        this.acceptStatement = builder.acceptStatement;
        this.statusProposal = builder.statusProposal;
        this.descriptionRejected = builder.descriptionRejected;
        this.creditKind = builder.creditKind;
    }

    public BuilderProposal toBuilder(){
        return new BuilderProposal()
                .withId(id)
                .withUserId(userId)
                .withAccountId(accountId)
                .withAmountOfCredit(amountOfCredit)
                .withMonth(month)
                .withSalary(salary)
                .withInterest(interest)
                .withCommission(commission)
                .withMonthlyFee(monthlyFee)
                .withCurrency(currency)
                .withCurrencySymbol(currencySymbol)
                .withUsername(username)
                .withPurpose(purpose)
                .withProposalNumber(proposalNumber)
                .withDateFrom(dateFrom)
                .withDateTo(dateTo)
                .withAcceptStatement(acceptStatement)
                .withStatusProposal(statusProposal)
                .withDescriptionRejected(descriptionRejected)
                .withCreditKind(creditKind);
    }

    public static BuilderProposal builder(){
        return new BuilderProposal();
    }

    @Override
    public String toString() {
        return "Proposal{" +
                "currency='" + currency + '\'' +
                '}';
    }

    public static class BuilderProposal{
        private BuilderProposal(){
        }
        public Proposal build(){
            return new Proposal(this);
        }
        private Long id;
        private Long userId;
        private Long accountId;
        private double amountOfCredit;
        private int month;
        private double salary;
        private double interest;
        private double commission;
        private double monthlyFee;
        private String currency;
        private String currencySymbol;
        private String username;
        private String purpose;
        private String proposalNumber;
        private LocalDate dateFrom;
        private LocalDate dateTo;
        private boolean acceptStatement;
        @Enumerated(EnumType.STRING)
        private StatusProposal statusProposal;
        @Enumerated(EnumType.STRING)
        private DescriptionRejected descriptionRejected;
        @Enumerated(EnumType.STRING)
        private CreditKind creditKind;

        public BuilderProposal withId(Long id) {
            this.id = id;
            return this;
        }

        public BuilderProposal withUserId(Long userId) {
            this.userId = userId;
            return this;
        }

        public BuilderProposal withAccountId(Long accountId) {
            this.accountId = accountId;
            return this;
        }

        public BuilderProposal withAmountOfCredit(double amountOfCredit) {
            this.amountOfCredit = amountOfCredit;
            return this;
        }

        public BuilderProposal withMonth(int month) {
            this.month = month;
            return this;
        }

        public BuilderProposal withSalary(double salary) {
            this.salary = salary;
            return this;
        }

        public BuilderProposal withInterest(double interest) {
            this.interest = interest;
            return this;
        }

        public BuilderProposal withCommission(double commission) {
            this.commission = commission;
            return this;
        }

        public BuilderProposal withMonthlyFee(double monthlyFee) {
            this.monthlyFee = monthlyFee;
            return this;
        }

        public BuilderProposal withCurrency(String currency) {
            this.currency = currency;
            return this;
        }

        public BuilderProposal withCurrencySymbol(String currencySymbol) {
            this.currencySymbol = currencySymbol;
            return this;
        }

        public BuilderProposal withUsername(String username) {
            this.username = username;
            return this;
        }

        public BuilderProposal withPurpose(String purpose) {
            this.purpose = purpose;
            return this;
        }

        public BuilderProposal withProposalNumber(String proposalNumber) {
            this.proposalNumber = proposalNumber;
            return this;
        }

        public BuilderProposal withDateFrom(LocalDate dateFrom) {
            this.dateFrom = dateFrom;
            return this;
        }

        public BuilderProposal withDateTo(LocalDate dateTo) {
            this.dateTo = dateTo;
            return this;
        }

        public BuilderProposal withAcceptStatement(boolean acceptStatement) {
            this.acceptStatement = acceptStatement;
            return this;
        }

        public BuilderProposal withStatusProposal(StatusProposal statusProposal) {
            this.statusProposal = statusProposal;
            return this;
        }

        public BuilderProposal withDescriptionRejected(DescriptionRejected descriptionRejected) {
            this.descriptionRejected = descriptionRejected;
            return this;
        }

        public BuilderProposal withCreditKind(CreditKind creditKind) {
            this.creditKind = creditKind;
            return this;
        }
    }
}
