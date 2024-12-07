package com.mockproject.givetoget.repository;

import com.mockproject.givetoget.entity.TransactionStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionStatusRepository extends JpaRepository<TransactionStatusEntity, Integer> {
}
