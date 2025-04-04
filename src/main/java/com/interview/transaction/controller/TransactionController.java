package com.interview.transaction.controller;

import com.interview.transaction.model.Transaction;
import com.interview.transaction.page.TransactionPage;
import com.interview.transaction.request.TransactionListReq;
import com.interview.transaction.response.TransactionResponse;
import com.interview.transaction.service.TransactionService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Resource
    private TransactionService transactionService;

    @PostMapping("/create")
    public TransactionResponse<String> create(@Valid @RequestBody Transaction transaction) {
        transactionService.createTransaction(transaction);
        return TransactionResponse.ok("SUCCESS");
    }

    @DeleteMapping("/delete/{id}")
    public TransactionResponse<String> delete(@PathVariable Long id){
        transactionService.deleteTransaction(id);
        return TransactionResponse.ok("SUCCESS");
    }

    @PostMapping("/modify")
    public TransactionResponse<Transaction> modify(@Valid @RequestBody Transaction transaction) {
        return TransactionResponse.ok(transactionService.modifyTransaction(transaction));
    }

    @PostMapping("/list")
    public TransactionResponse<TransactionPage<Transaction>> list(@Valid @RequestBody TransactionListReq transactionListReq){
        return TransactionResponse.ok(transactionService.list(transactionListReq));
    }

}