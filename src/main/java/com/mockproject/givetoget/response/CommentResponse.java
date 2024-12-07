package com.mockproject.givetoget.response;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentResponse {
    private int authorId;
    private String authorName;
    private String comment;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
}
