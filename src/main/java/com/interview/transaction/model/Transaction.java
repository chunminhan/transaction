package com.interview.transaction.model;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

/*
There should have other properties.
Assume that Transaction class looks like the below definition
 */
public class Transaction {

    @Positive(message = "The Unique Identifier of the transaction must be greater than 0")
    private long id;

    @Positive(message = "The amount of the transaction must be greater than 0")
    private long amount;

    @Pattern(regexp = "^((([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29))\\s+([0-1]?[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])$",
            message = "The required format of datetime：yyyy-MM-dd HH:mm:ss")
    private String timestamp;
    private String remarks;

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

}
