package com.mockproject.givetoget.jwt;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfo {
    private String email;
    private String role;
}
