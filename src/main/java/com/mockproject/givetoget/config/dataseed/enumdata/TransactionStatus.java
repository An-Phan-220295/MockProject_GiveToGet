package com.mockproject.givetoget.config.dataseed.enumdata;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TransactionStatus {
    OPENING(1, "OPENING"),
    PROCESSING(2,"PROCESSING"),
    COMPLETED(2,"COMPLETED");
    private final int id;
    private final String transactionStatus;}
