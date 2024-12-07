package com.mockproject.givetoget.repository;

import com.mockproject.givetoget.entity.RequestEntity;
import com.mockproject.givetoget.entity.TransactionEntity;
import com.mockproject.givetoget.entity.UserInforEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Integer> {
    boolean existsByPartnerAndRequest(UserInforEntity partner, RequestEntity request);
    TransactionEntity findByPartnerAndRequest(UserInforEntity partner, RequestEntity request);
}
