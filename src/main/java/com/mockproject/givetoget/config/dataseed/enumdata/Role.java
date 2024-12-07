package com.mockproject.givetoget.config.dataseed.enumdata;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
    USER(1, "ROLE_USER"),
    ADMIN(2,"ROLE_ADMIN");
    private final int id;
    private final String roleName;
}
