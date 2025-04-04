package com.interview.transaction.service.impl;

import com.interview.transaction.model.Transaction;
import com.interview.transaction.page.TransactionPage;
import com.interview.transaction.page.TransactionPageHelper;
import com.interview.transaction.repository.TransactionRepository;
import com.interview.transaction.request.TransactionListReq;
import com.interview.transaction.service.TransactionService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ConcurrentSkipListSet;

@Service
public class TransactionServiceImp implements TransactionService {

    @Resource
    private TransactionRepository transactionRepository;

    @Override
    public void createTransaction(final Transaction transaction){
        transactionRepository.createTransaction(transaction);
    }

    @Override
    public void deleteTransaction(final Long id){
        transactionRepository.deleteTransaction(id);
    }

    @Override
    public Transaction modifyTransaction(final Transaction transaction){
        return transactionRepository.modifyTransaction(transaction);
    }

    @Override
    public TransactionPage<Transaction> list(final TransactionListReq transactionListReq){
        //Get the the snapshot of the transaction based on the clientId
        //Save the snapshot of the transaction for the terminate id to keep consistency for the subsequent page inquiry
        List<Long> transactionIds = TransactionPageHelper.getTransactionIds(transactionListReq.getTerminateId());
        if(transactionIds == null){
            //This is the first time, or the access is time-out. Refresh the snapshot of transaction first
            transactionIds = transactionRepository.getTransactionIdSetSnapshot();
            TransactionPageHelper.saveTransactionIds(transactionListReq.getTerminateId(), transactionIds);
        }else if(transactionListReq.getReqPageIdx() == 0){
            //Return the first page again, Checking the transaction repository version to decide if refreshing
            //the snapshot of transaction set
            if(transactionListReq.getTransactionVersion() != transactionRepository.getTransactionRepositoryVersion()){
                transactionIds = transactionRepository.getTransactionIdSetSnapshot();
            }
        }

        int total = transactionIds.size();
        int fromIndex = transactionListReq.getReqPageIdx() * transactionListReq.getPageSize();
        int toIndex = Math.min(fromIndex + transactionListReq.getPageSize(), total);

        return transactionRepository.listTransaction(transactionIds, fromIndex, toIndex, new TransactionPage<>());
    }

}
