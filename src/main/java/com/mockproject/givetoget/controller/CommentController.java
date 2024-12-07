package com.mockproject.givetoget.controller;

import com.mockproject.givetoget.request.CommentRequest;
import com.mockproject.givetoget.response.BaseResponse;
import com.mockproject.givetoget.service.CommentService;
import com.mockproject.givetoget.utils.CodeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/v1/comment/request/{requestId}/transaction/{transactionId}")
    public ResponseEntity<?> getAllComment(@PathVariable Optional<Integer> requestId, @PathVariable Optional<Integer> transactionId) {
        Integer request = requestId.orElse(null);  //
        Integer transaction = transactionId.orElse(null);
        BaseResponse baseResponse = BaseResponse.builder()
                .code(CodeMessage.SUCCESS.getCode())
                .message(CodeMessage.SUCCESS.getMessage())
                .data(commentService.getAllComment(request, transaction))
                .build();
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PostMapping("/v1/comment")
    public ResponseEntity<?> saveComment(@RequestBody CommentRequest commentRequest) {
        BaseResponse baseResponse = BaseResponse.builder()
                .code(CodeMessage.SUCCESS.getCode())
                .message(CodeMessage.SUCCESS.getMessage())
                .data(commentService.saveComment(commentRequest))
                .build();
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
