package com.mainapp.web.controller;

import com.mainapp.service.MainService;
import com.mainapp.service.data.User;
import com.mainapp.service.mapper.UserAccountsMapper;
import com.mainapp.web.dto.TransferDto;
import com.mainapp.web.dto.UserAccountDto;
import com.mainapp.web.feign.FeignServiceAccountsManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.TreeSet;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/dashboard")
public class DashboardController {

    private final FeignServiceAccountsManager feignServiceAccountsManager;
    private final MainService mainService;
    private final UserAccountsMapper userAccountsMapper;

    @GetMapping
    public String getDashboard(@AuthenticationPrincipal User user, ModelMap modelMap) {
        try {
            TreeSet<UserAccountDto> accounts = feignServiceAccountsManager.getAllUserAccountsByUserId(user.getId());
            if (accounts.size() == 0) {
                try {
                    mainService.createAccountForUser(user.getId(), userAccountsMapper.mapToUserAccountFromUserAccountDto(new UserAccountDto()));
                    modelMap.put("quickTransfer",new TransferDto());
                    return "redirect:/dashboard";
                } catch (Exception e) {
                    modelMap.put("error", "Failed with loading your accounts");
                }
            }
            modelMap.put("userBankAccounts", accounts);
        } catch (Exception e) {
            modelMap.put("error", "Failed with loading your accounts");
            modelMap.put("userBankAccounts", new TreeSet<UserAccountDto>());
        }
        modelMap.put("quickTransfer",new TransferDto());
        return "dashboard";
    }

    @GetMapping("/create-account")
    public String getAccount(ModelMap modelMap) {
        modelMap.put("userAccount", new UserAccountDto());
        return "account";
    }

    @PostMapping("/create-account")
    public String postAccount(@AuthenticationPrincipal User user, @ModelAttribute UserAccountDto userAccountDto, ModelMap modelMap) {
        log.info("paczka "+userAccountDto.getCurrency());
        try {
            mainService.createAccountForUser(user.getId(), userAccountsMapper.mapToUserAccountFromUserAccountDto(userAccountDto));
        } catch (Exception e) {
            modelMap.put("error", "Failed with creating account");
            modelMap.put("userAccount",new UserAccountDto());
            return "account";
        }
        return "redirect:/dashboard";
    }
    @PostMapping
    public String quickTransfer(@AuthenticationPrincipal User user, @ModelAttribute TransferDto transferDto, ModelMap modelMap){
        try {
            if (!mainService.quickTransferToUserByAccountNumber(user,transferDto,modelMap)) {
                modelMap.put("quickTransfer", new TransferDto());
                return "dashboard";
            }
        } catch (Exception e){
            modelMap.put("error","There was an error creating transaction");
        }
        return "redirect:/dashboard";
    }
}
