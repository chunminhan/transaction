package com.interview.transaction.repository;

import com.interview.transaction.exception.TransactionException;
import com.interview.transaction.model.Transaction;
import com.interview.transaction.page.TransactionPage;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class TransactionRepository {

    private final Map<Long, Transaction> transactions = new ConcurrentHashMap<>();
    private final ConcurrentSkipListSet<Long> sortedTransactionIdSet = new ConcurrentSkipListSet<>();
    private final AtomicLong transactionRepoVersion = new AtomicLong(Long.MIN_VALUE);

    public void createTransaction(final Transaction transaction){
        Transaction t = transactions.putIfAbsent(transaction.getId(), transaction);
        if(t == null){
            sortedTransactionIdSet.add(transaction.getId());
            transactionRepoVersion.incrementAndGet();
        }
        else throw new TransactionException.DuplicateEntityException("Duplicate Transaction");
    }

    public void deleteTransaction(final Long id){
        if(transactions.get(id) == null)
            throw new TransactionException.EntityNotFoundException("Transaction Not Found");
        transactions.remove(id);
        sortedTransactionIdSet.remove(id);
        //Not accurately control, performance consideration
        transactionRepoVersion.incrementAndGet();
    }

    public Transaction modifyTransaction(final Transaction transaction){
        if(transactions.get(transaction.getId()) == null)
            throw new TransactionException.EntityNotFoundException("Transaction Not Found");
        transactions.put(transaction.getId(), transaction);
        transactionRepoVersion.incrementAndGet();
        return transaction;
    }

    public TransactionPage<Transaction> listTransaction(List<Long> transactionIds, int fromIndex, int toIndex, TransactionPage<Transaction> transactionTransactionPage){

        List<Transaction> transactionList =  transactionIds
                .subList(fromIndex, toIndex)
                .stream()
                .map(transactions::get)
                .toList();

        transactionTransactionPage.setPageContent(transactionList);

        return transactionTransactionPage;
    }

    public List<Long> getTransactionIdSetSnapshot(){
        return new ArrayList<>(sortedTransactionIdSet.descendingSet());
    }

    public long getTransactionRepositoryVersion(){
        return transactionRepoVersion.get();
    }
}
