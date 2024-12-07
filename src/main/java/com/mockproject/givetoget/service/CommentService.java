package com.mockproject.givetoget.service;

import com.mockproject.givetoget.request.CommentRequest;
import com.mockproject.givetoget.response.CommentResponse;

import java.util.List;

public interface CommentService {
    CommentResponse saveComment(CommentRequest commentRequest);
    List<CommentResponse> getAllComment(Integer requestId, Integer transactionId);
}
