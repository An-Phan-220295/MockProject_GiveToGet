package com.mockproject.givetoget.service.impl;

import com.mockproject.givetoget.config.dataseed.enumdata.ConfirmStatus;
import com.mockproject.givetoget.config.dataseed.enumdata.TransactionStatus;
import com.mockproject.givetoget.entity.*;
import com.mockproject.givetoget.repository.*;
import com.mockproject.givetoget.request.CommentRequest;
import com.mockproject.givetoget.response.CommentResponse;
import com.mockproject.givetoget.service.CommentService;
import com.mockproject.givetoget.utils.CodeMessage;
import com.mockproject.givetoget.utils.Utils;
import com.mockproject.givetoget.utils.exception.BaseException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private Utils utils;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private RequestRepository requestRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private TransactionStatusRepository transactionStatusRepository;
    @Autowired
    private TransactionConfirmStatusRepository transactionConfirmStatusRepository;

    @Override
    public CommentResponse saveComment(CommentRequest commentRequest) {
        String currentUser = utils.getCurrentUser();
        AccountEntity accountEntity = accountRepository.findByEmail(currentUser)
                .orElseThrow(() -> new BaseException(CodeMessage.USER_NOT_FOUND));

        if (commentRequest.getIdRequest() != null) {
            RequestEntity requestEntity = requestRepository.findById(commentRequest.getIdRequest())
                    .orElseThrow(() -> new BaseException(CodeMessage.REQUEST_NOT_FOUND));

            TransactionEntity transaction = transactionRepository
                    .findByPartnerAndRequest(accountEntity.getUserInfor(), requestEntity);

            if (transaction != null) {
                return addComment(transaction, accountEntity.getUserInfor(), commentRequest);
            } else {
                TransactionEntity newTransaction = createNewTransaction(accountEntity.getUserInfor(), requestEntity);
                return addComment(newTransaction, accountEntity.getUserInfor(), commentRequest);
            }
        } else if (commentRequest.getIdTransaction() != null) {
            TransactionEntity transactionEntity = transactionRepository.findById(commentRequest.getIdTransaction())
                    .orElseThrow(() -> new BaseException(CodeMessage.TRANSACTION_NOT_FOUND));

            return addComment(transactionEntity, accountEntity.getUserInfor(), commentRequest);
        } else {
            throw new BaseException(CodeMessage.INVALID_REQUEST);
        }
    }

    @Override
    public List<CommentResponse> getAllComment(Integer requestId, Integer transactionId) {
        List<CommentEntity> commentEntities;

        if (requestId != null && requestId != 0) {
            String currentUser = utils.getCurrentUser();
            AccountEntity accountEntity = accountRepository.findByEmail(currentUser)
                    .orElseThrow(() -> new BaseException(CodeMessage.USER_NOT_FOUND));
            RequestEntity requestEntity = requestRepository.findById(requestId)
                    .orElseThrow(() -> new BaseException(CodeMessage.REQUEST_NOT_FOUND));

            commentEntities = commentRepository
                    .findByTransactionRequestAndTransactionPartner(requestEntity, accountEntity.getUserInfor());

        } else if (transactionId != null && transactionId != 0) {
            TransactionEntity transactionEntity = transactionRepository.findById(transactionId)
                    .orElseThrow(() -> new BaseException(CodeMessage.TRANSACTION_NOT_FOUND));

            commentEntities = commentRepository.findByTransaction(transactionEntity);

        } else {
            throw new BaseException(CodeMessage.INVALID_REQUEST);
        }

        commentEntities.sort(Comparator.comparing(CommentEntity::getCreatedDate));

        return commentEntities.stream()
                .map(commentEntity -> {
                    CommentResponse commentResponse = modelMapper.map(commentEntity, CommentResponse.class);

                    commentResponse.setAuthorId(commentEntity.getAuthor().getId());
                    commentResponse.setAuthorName(commentEntity.getAuthor().getUsername());

                    return commentResponse;
                })
                .collect(Collectors.toList());
    }

    private CommentResponse addComment(TransactionEntity transaction, UserInforEntity userInfor, CommentRequest commentRequest) {
        CommentEntity commentEntity = CommentEntity.builder()
                .author(userInfor)
                .transaction(transaction)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .comment(commentRequest.getComment())
                .build();
        CommentEntity savedComment = commentRepository.save(commentEntity);

        if (savedComment.getId() != 0) {
            CommentResponse commentResponse = modelMapper.map(savedComment, CommentResponse.class);
            commentResponse.setAuthorId(savedComment.getAuthor().getId());
            commentResponse.setAuthorName(savedComment.getAuthor().getUsername());
            return commentResponse;
        } else {
            throw new BaseException(CodeMessage.COMMENT_ERROR);
        }
    }

    private TransactionEntity createNewTransaction(UserInforEntity userInfor, RequestEntity requestEntity) {
        TransactionEntity transaction = TransactionEntity.builder()
                .partner(userInfor)
                .request(requestEntity)
                .status(transactionStatusRepository.findById(TransactionStatus.OPENING.getId()).orElse(null))
                .partnerConfirmStatus(transactionConfirmStatusRepository.findById(ConfirmStatus.WAITING.getId()).orElse(null))
                .userConfirmStatus(transactionConfirmStatusRepository.findById(ConfirmStatus.WAITING.getId()).orElse(null))
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();
        return transactionRepository.save(transaction);
    }
}
