package com.mockproject.givetoget.config.dataseed.enumdata;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RequestType {
    RECEIVED_REQUEST(1, "RECEIVED_REQUEST"),
    GIVEN_REQUEST(2,"GIVEN_REQUEST");
    private final int id;
    private final String requestType;
}
