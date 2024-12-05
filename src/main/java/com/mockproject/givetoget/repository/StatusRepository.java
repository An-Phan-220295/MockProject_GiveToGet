package com.mockproject.givetoget.repository;

import com.mockproject.givetoget.entity.StatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<StatusEntity, Integer> {
    StatusEntity findById(int id);
}
