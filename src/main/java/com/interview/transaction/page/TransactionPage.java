package com.interview.transaction.page;

import java.util.List;

public class TransactionPage<T> {
    private long totalPages;
    private long requestPageIdx;
    private long pageSize;
    private long transactionVersion;

    private List<T> pageContent;

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public long getRequestPageIdx() {
        return requestPageIdx;
    }

    public void setRequestPageIdx(long requestPageIdx) {
        this.requestPageIdx = requestPageIdx;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public long getTransactionVersion() {
        return transactionVersion;
    }

    public void setTransactionVersion(long transactionVersion) {
        this.transactionVersion = transactionVersion;
    }

    public List<T> getPageContent() {
        return pageContent;
    }

    public void setPageContent(List<T> pageContent) {
        this.pageContent = pageContent;
    }

}
