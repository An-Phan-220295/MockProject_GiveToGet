package com.mockproject.givetoget.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentRequest {
    private Integer idRequest;
    private Integer idTransaction;
    private String comment;
}
