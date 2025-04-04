package com.interview.transaction.page;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

public class TransactionPageHelper {
    //Save the relation between client id and transaction id set
    //Keep every page-based query is consistency and independence
    //Should have an expiration cleanup mechanism, for example, clear the transaction set when corresponding access token is expired
    //Due the lacking of the cleanup mechanism will lead the high memory consumption
    private static ConcurrentHashMap<String, List<Long>> terminateId2TransactionIdSet = new ConcurrentHashMap<>();

    public static void saveTransactionIds(final String terminateId, List<Long> transactionIds){
        terminateId2TransactionIdSet.put(terminateId, transactionIds);
    }

    public static List<Long> getTransactionIds(final String terminateId){
        return terminateId2TransactionIdSet.get(terminateId);
    }

    public static boolean hasClientTransactionIds(final String terminateId){
        return terminateId2TransactionIdSet.containsKey(terminateId);
    }
}
