package com.accountsmanager.service.mapper;

import com.accountsmanager.service.data.Transfer;
import com.accountsmanager.web.dto.TransferDto;
import org.springframework.stereotype.Service;

@Service
public class TransferMapper {
    public Transfer mapToTransferFromTransferDto(final TransferDto transferDto) {
        return new Transfer(
                transferDto.getUserReceiveId(),
                transferDto.getAccountReceiveId(),
                transferDto.getAmount(),
                transferDto.getAccountNumber()
        );
    }
    public TransferDto mapToUserTransferDtoFromUserTransfer(final Transfer transfer){
        return new TransferDto(
                transfer.getUserReceiveId(),
                transfer.getAccountReceiveId(),
                transfer.getAmount(),
                transfer.getAccountNumber()
        );
    }
}
