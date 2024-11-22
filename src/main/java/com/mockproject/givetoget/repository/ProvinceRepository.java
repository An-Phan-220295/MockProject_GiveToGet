package com.mockproject.givetoget.repository;

import com.mockproject.givetoget.entity.ProvincesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinceRepository extends JpaRepository<ProvincesEntity, String> {
}

