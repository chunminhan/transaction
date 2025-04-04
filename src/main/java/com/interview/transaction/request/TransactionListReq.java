package com.interview.transaction.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class TransactionListReq {

    private String terminateId;

    @NotNull(message = "The page index requested can't be NULL")
    @Min(value = 0, message = "The page index requested can't be less than 0")
    private int reqPageIdx;

    @NotNull(message = "The page size requested can't be NULL")
    @Min(value = 1, message = "The page size requested must be greater than 0")
    private int pageSize;
    private long transactionVersion;

    public String getTerminateId() {
        return terminateId;
    }

    public void setTerminateId(String terminateId) {
        this.terminateId = terminateId;
    }

    public int getReqPageIdx() {
        return reqPageIdx;
    }

    public void setReqPageIdx(int reqPageIdx) {
        this.reqPageIdx = reqPageIdx;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTransactionVersion() {
        return transactionVersion;
    }

    public void setTransactionVersion(long transactionVersion) {
        this.transactionVersion = transactionVersion;
    }
}
