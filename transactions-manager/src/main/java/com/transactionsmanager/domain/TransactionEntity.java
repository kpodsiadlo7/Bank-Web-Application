package com.transactionsmanager.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "transactions_db")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long accountId;
    private String description;
    private String kindTransaction;
    private LocalDate transactionDate;
    private String value;

    public TransactionEntity(final Long userId, final Long accountId, final String description, final String kindTransaction, final String value) {
        this.userId = userId;
        this.accountId = accountId;
        this.description = description;
        this.kindTransaction = kindTransaction;
        this.transactionDate = LocalDate.now();
        this.value = value;
    }
}
