package com.mockproject.givetoget.repository;

import com.mockproject.givetoget.entity.CommentEntity;
import com.mockproject.givetoget.entity.RequestEntity;
import com.mockproject.givetoget.entity.TransactionEntity;
import com.mockproject.givetoget.entity.UserInforEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
    List<CommentEntity> findByTransactionRequestAndTransactionPartner(RequestEntity request, UserInforEntity user);
    List<CommentEntity> findByTransaction(TransactionEntity transaction);
}
