package com.mainapp.account;

import com.mainapp.account.dto.AccountDto;
import com.mainapp.transfer.dto.TransferDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.TreeSet;

@FeignClient(value = "accounts-manager", url = "http://accounts-manager:8010")
interface FeignServiceAccountsManager {

    @GetMapping("/get-accounts")
    TreeSet<AccountDto> getAllAccountsByUserId(@RequestParam Long userId);

    @GetMapping("/get-account")
    AccountDto getAccountByAccountId(@RequestParam Long accountId);

    @PostMapping("/quick-transfer")
    TransferDto quickTransfer(@RequestParam Long userId,
                              @RequestParam String kindTransaction,
                              @RequestBody TransferDto transferDto);
    @PostMapping("/create-account")
    AccountDto createAccountForUser(@RequestParam Long userId,
                                    @RequestBody AccountDto accountDto);

    @PostMapping("/set-commitments")
    void setCommitmentsToAccount(@RequestParam Long accountId,
                                 @RequestParam double monthlyFee);
}
