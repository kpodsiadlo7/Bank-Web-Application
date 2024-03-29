package com.transactionsmanager.transfer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransferDto {
    private Long userReceiveId;
    private Long accountReceiveId;
    private BigDecimal amount;
    private String accountNumber;
}
