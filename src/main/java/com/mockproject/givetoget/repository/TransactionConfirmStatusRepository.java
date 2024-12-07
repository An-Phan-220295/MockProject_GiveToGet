package com.mockproject.givetoget.repository;

import com.mockproject.givetoget.entity.TransactionConfirmStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionConfirmStatusRepository extends JpaRepository<TransactionConfirmStatusEntity, Integer> {
}
