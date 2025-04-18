package com.example.BankingApplication.controller;

import com.example.BankingApplication.model.LoanModel;
import com.example.BankingApplication.model.NetBankingModel;
import com.example.BankingApplication.model.UserAccountModel;
import com.example.BankingApplication.model.UserPassbookModel;
import com.example.BankingApplication.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userAccount")
public class AccountController {

    @Autowired
    private AccountService accountService;


    @PostMapping("/addingAccount")
    public ResponseEntity<UserAccountModel> insertedUserAccount(@RequestBody UserAccountModel userAccountModel) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authenticatedEmail = authentication.getName();
        return ResponseEntity.ok(accountService.addUserAccount(userAccountModel, authenticatedEmail));
    }

    @GetMapping("/passbook")
    public ResponseEntity<UserPassbookModel> passBookDetail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authenticatedEmail = authentication.getName();
        return new ResponseEntity<>(accountService.getPassbookDetail(authenticatedEmail), HttpStatus.OK);
    }


    @PutMapping("/netBanking")
    public ResponseEntity<NetBankingModel> netBanking(
                        @RequestParam Long senderAccountNumber,
                        @RequestParam Long receiverAccountNumber,
                        @RequestParam Long amount){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authenticatedEmail = authentication.getName();
        return new ResponseEntity<>(accountService.moneyTransfer(senderAccountNumber, receiverAccountNumber, amount), HttpStatus.OK);
    }


    @GetMapping("/loanCalculator")
    public ResponseEntity<LoanModel> calculateLoan(
                        @RequestParam double principal,
                        @RequestParam double rateOfInterest,
                        @RequestParam double year){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authenticatedEmail = authentication.getName();
        return new ResponseEntity<>(accountService.loanCalculate(principal, rateOfInterest, year), HttpStatus.OK);
    }

}
