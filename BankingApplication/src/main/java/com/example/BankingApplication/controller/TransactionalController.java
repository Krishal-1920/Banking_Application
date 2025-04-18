package com.example.BankingApplication.controller;


import com.example.BankingApplication.model.TransactionalModel;
import com.example.BankingApplication.service.TransactionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionalController {

    @Autowired
    private TransactionalService transactionalService;


    // Deposit
    @PutMapping("/deposit")
    public ResponseEntity<TransactionalModel> addBalance(@RequestBody TransactionalModel transactionalModel){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authenticatedEmail = authentication.getName();
        return ResponseEntity.ok(transactionalService.deposit(transactionalModel));
    }

    // Withdrawal
    @PutMapping("/withdrawal")
    public ResponseEntity<TransactionalModel> deleteBalance(@RequestBody TransactionalModel transactionalModel){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authenticatedEmail = authentication.getName();
        return ResponseEntity.ok(transactionalService.withdrawal(transactionalModel));
    }


    @GetMapping("/currentBalance/{accountNumber}")
    public TransactionalModel getBankBalance(@PathVariable Long accountNumber){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authenticatedEmail = authentication.getName();
        return transactionalService.listBankBalance(accountNumber);
    }

}
