package com.mockproject.givetoget.repository;

import com.mockproject.givetoget.entity.WardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WardRepository extends JpaRepository<WardEntity, String> {
    List<WardEntity> findByDistrict_Code(String districtCode);
}

