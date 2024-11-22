package com.mockproject.givetoget.repository;

import com.mockproject.givetoget.entity.DistrictEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<DistrictEntity, String> {
    List<DistrictEntity> findByProvince_Code(String provinceCode);
}
