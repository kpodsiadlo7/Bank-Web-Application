package com.proposalmanager.proposal;

import com.proposalmanager.credit.enums.CreditKind;
import com.proposalmanager.proposal.enums.DescriptionRejected;
import com.proposalmanager.proposal.enums.StatusProposal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "proposals")
class ProposalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    // credit kind to delete because already exist in db
    @Enumerated(EnumType.STRING)
    private CreditKind creditKind;

    public ProposalEntity(final Long userId, final Long accountId, final double amountOfCredit, final int month, final double salary, final double interest, final double commission, final double monthlyFee, final String currency, final String currencySymbol, final String username, final String purpose, final String proposalNumber, final LocalDate dateFrom, final LocalDate dateTo, final boolean acceptStatement, final StatusProposal statusProposal, final DescriptionRejected descriptionRejected, final CreditKind creditKind) {
        this.userId = userId;
        this.accountId = accountId;
        this.amountOfCredit = amountOfCredit;
        this.month = month;
        this.salary = salary;
        this.interest = interest;
        this.commission = commission;
        this.monthlyFee = monthlyFee;
        this.currency = currency;
        this.currencySymbol = currencySymbol;
        this.username = username;
        this.purpose = purpose;
        this.proposalNumber = proposalNumber;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.acceptStatement = acceptStatement;
        this.statusProposal = statusProposal;
        this.descriptionRejected = descriptionRejected;
        this.creditKind = creditKind;
    }
}
