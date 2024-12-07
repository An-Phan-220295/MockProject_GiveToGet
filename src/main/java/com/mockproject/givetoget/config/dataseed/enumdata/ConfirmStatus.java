package com.mockproject.givetoget.config.dataseed.enumdata;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ConfirmStatus {
    WAITING(1, "WAITING"),
    ACCEPTED(2,"ACCEPTED"),
    COMPLETED(3, "COMPLETED");
    private final int id;
    private final String confirmStatus;
}
