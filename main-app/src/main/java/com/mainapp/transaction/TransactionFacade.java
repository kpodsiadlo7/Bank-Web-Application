package com.mainapp.transaction;

import com.mainapp.account.AccountFacade;
import com.mainapp.account.dto.AccountDto;
import com.mainapp.proposal.dto.ProposalDto;
import com.mainapp.proposal.ProposalFacade;
import com.mainapp.transaction.dto.TransactionDto;
import com.mainapp.transfer.dto.TransferDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionFacade {

    private final AccountFacade accountFacade;
    private final FeignServiceTransactionsManager feignServiceTransactionsManager;
    private final ProposalFacade proposalFacade;


    public String getAccounts(final Long accountId, final ModelMap modelMap) {
        modelMap.put("quickTransfer", new TransferDto());
        try {
            AccountDto accountDto = accountFacade.getAccountByAccountId(accountId);
            modelMap.put("account", accountDto);

        } catch (Exception e) {
            modelMap.put("error", "Failed with loading your account");
            modelMap.put("account", AccountDto.builder().build());
        }
        return getTransactionsByAccountId(accountId,modelMap);
    }

    private String getTransactionsByAccountId(final Long accountId, final ModelMap modelMap) {
        Set<TransactionDto> transactions = new HashSet<>();
        try {
            transactions = feignServiceTransactionsManager.getTransactionsByAccountId(accountId);
            TreeSet<TransactionDto> sortedTransactions = new TreeSet<>(transactions);
            modelMap.put("transactionsDto",sortedTransactions);
        } catch (Exception e){
            modelMap.put("error", "Problem with fetching your transactions");
            modelMap.put("transactionsDto", transactions);
            return "account";
        }
        return "account";
    }

    public String getAllTransactionsByUserId(final Long userId, ModelMap modelMap) {
        Set<TransactionDto> transactions = new HashSet<>();
        try {
            transactions = feignServiceTransactionsManager.getAllTransactionByUserId(userId);
            TreeSet<TransactionDto> sortedTransactions = new TreeSet<>(transactions);
            modelMap.put("transactionsDto", sortedTransactions);
        } catch (Exception e) {
            modelMap.put("error", "Problem with fetching your transactions");
            modelMap.put("transactionsDto", transactions);
            return "transactions";
        }
        return "transactions";
    }

    public String getAllProposalsByUserId(final Long userId, ModelMap modelMap) {
        Set<ProposalDto> proposals = new HashSet<>();
        try {
            proposals = proposalFacade.getAllProposalsByUserId(userId);
            TreeSet<ProposalDto> sortedById = new TreeSet<>(proposals);
            modelMap.put("proposalsDto", sortedById);
        } catch (Exception e) {
            modelMap.put("error", "Problem with fetching your proposals");
            modelMap.put("proposalsDto", proposals);
            return "proposals";
        }
        return "proposals";
    }

    public TransactionDto makeTransaction(Long userId, Long thisAccountId, String descriptionTransaction, TransferDto transferDto) {
        return feignServiceTransactionsManager.makeTransaction(userId, thisAccountId, descriptionTransaction, transferDto);
    }
}
