package com.mainapp.web.controller;

import com.mainapp.service.controller.CreditService;
import com.mainapp.service.controller.ProposalService;
import com.mainapp.service.data.Proposal;
import com.mainapp.service.data.User;
import com.mainapp.service.mapper.AccountMapper;
import com.mainapp.service.mapper.ProposalMapper;
import com.mainapp.web.dto.AccountDto;
import com.mainapp.web.dto.ProposalDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/dashboard/credit")
public class CreditController {

    private final CreditService creditService;
    private final ProposalService proposalService;
    private final ProposalMapper proposalMapper;

    @GetMapping
    public String getCredit(@AuthenticationPrincipal User user,
                            ModelMap modelMap) {
        return creditService.getCredits(user, modelMap);
    }

    @GetMapping("{proposalNumber}")
    public String getProposal(@PathVariable String proposalNumber, ModelMap modelMap) {
        return proposalService.getProposal(proposalNumber, modelMap);
    }

    @PostMapping
    public String prepareProposal(@AuthenticationPrincipal User user,
                                  @ModelAttribute ProposalDto proposalDto,
                                  @RequestParam(name = "accountId") Long accountId, ModelMap modelMap,
                                  @RequestParam(name = "creditKind") String creditKind,
                                  @RequestParam(name = "promotion") String promotion) {
        log.info("should be 3 " + proposalDto.getMonth());
        return proposalService.validateBeforePost(user,
                proposalMapper.mapToProposalFromProposalDto(proposalDto), accountId, modelMap, creditKind, promotion);
    }

    @PostMapping("{proposalNumber}")
    public String postProposal(@RequestParam(name = "accountId") Long accountId,
                               @RequestParam(name = "monthlyFee") double monthlyFee,
                               @PathVariable String proposalNumber,
                               ModelMap modelMap) {
        return proposalService.postProposal(accountId,monthlyFee,proposalNumber, modelMap);
    }
}
