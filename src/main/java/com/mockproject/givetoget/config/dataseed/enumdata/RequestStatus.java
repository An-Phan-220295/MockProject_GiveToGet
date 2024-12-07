package com.mockproject.givetoget.config.dataseed.enumdata;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RequestStatus {
    OPENING(1, "OPENING"),
    CLOSED(2,"CLOSED");
    private final int id;
    private final String requestStatus;
}
