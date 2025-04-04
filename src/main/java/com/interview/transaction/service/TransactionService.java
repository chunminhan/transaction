package com.interview.transaction.service;

import com.interview.transaction.model.Transaction;
import com.interview.transaction.page.TransactionPage;
import com.interview.transaction.request.TransactionListReq;

public interface TransactionService {
    void createTransaction(final Transaction transaction);
    void deleteTransaction(final Long id);
    Transaction modifyTransaction(final Transaction transaction);
    TransactionPage<Transaction> list(final TransactionListReq transactionListReq);
}
