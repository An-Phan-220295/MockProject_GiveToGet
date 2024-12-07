package com.mockproject.givetoget.repository;

import com.mockproject.givetoget.entity.RequestTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestTypeRepository extends JpaRepository<RequestTypeEntity, Integer> {
}
