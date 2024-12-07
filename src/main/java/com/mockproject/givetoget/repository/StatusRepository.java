package com.mockproject.givetoget.repository;

import com.mockproject.givetoget.entity.RequestStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<RequestStatusEntity, Integer> {
    RequestStatusEntity findById(int id);
}
