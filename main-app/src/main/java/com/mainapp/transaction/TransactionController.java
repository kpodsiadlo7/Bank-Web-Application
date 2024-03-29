package com.mainapp.transaction;

import com.mainapp.dashboard.DashboardFacade;
import com.mainapp.user.User;
import com.mainapp.transfer.dto.TransferDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/dashboard")
class TransactionController {
    private final DashboardFacade dashboardFacade;
    private final TransactionFacade transactionFacade;

    @GetMapping("/transactions")
    String getAllTransactionsByUserId(@AuthenticationPrincipal User user, ModelMap modelMap) {
        return transactionFacade.getAllTransactionsByUserId(user.getId(), modelMap);
    }

    @GetMapping("/proposals")
    String getAllProposalsByUserId(@AuthenticationPrincipal User user, ModelMap modelMap) {
        return transactionFacade.getAllProposalsByUserId(user.getId(), modelMap);
    }

    @GetMapping("/account/{accountId}")
    String getAccounts(@PathVariable Long accountId, ModelMap modelMap) {
        return transactionFacade.getAccounts(accountId, modelMap);
    }


    @PostMapping("/account/{accountId}")
    String makeTransaction(@AuthenticationPrincipal User user, @PathVariable Long accountId, @ModelAttribute TransferDto transferDto,
                                  @RequestParam(name = "descriptionTransaction") String descriptionTransaction, ModelMap modelMap) {
        if (dashboardFacade.makeTransaction(user, accountId, transferDto, descriptionTransaction, modelMap))
            return "account";

        return "redirect:/dashboard/account/" + accountId;
    }
}
