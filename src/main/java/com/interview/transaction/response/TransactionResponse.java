package com.interview.transaction.response;

public class TransactionResponse<T> {
    private Status status;

    private T payload;
    private String errorMsg;

    private enum Status {
        OK, BAD_REQUEST, NOT_FOUND, DUPLICATE_ENTITY
    }

    public static <T> TransactionResponse<T> badRequest(String errorMsg) {
        TransactionResponse<T> response = new TransactionResponse<>();
        response.setStatus(Status.BAD_REQUEST);
        response.setErrorMsg(errorMsg);
        return response;
    }

    public static <T> TransactionResponse<T> ok(T payload) {
        TransactionResponse<T> response = new TransactionResponse<>();
        response.setStatus(Status.OK);
        response.setPayload(payload);
        return response;
    }

    public static <T> TransactionResponse<T> entityNotFound(T payload) {
        TransactionResponse<T> response = new TransactionResponse<>();
        response.setStatus(Status.NOT_FOUND);
        response.setPayload(payload);
        return response;
    }

    public static <T> TransactionResponse<T> entityDuplicate(T payload) {
        TransactionResponse<T> response = new TransactionResponse<>();
        response.setStatus(Status.DUPLICATE_ENTITY);
        response.setPayload(payload);
        return response;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
