package com.mockproject.givetoget.repository;

import com.mockproject.givetoget.entity.RequestEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RequestRepository extends JpaRepository<RequestEntity, Integer> {
    @Query("SELECT r FROM RequestEntity r " +
            "WHERE r.type = :type " +
            "AND r.status.status = :status " +
            "AND (:provinceCode IS NULL OR LOWER(r.user.address.ward.district.province.code) LIKE CONCAT('%', LOWER(COALESCE(CAST(:provinceCode AS String), '')), '%')) " +
            "AND (:districtCode IS NULL OR LOWER(r.user.address.ward.district.code) LIKE CONCAT('%', LOWER(COALESCE(CAST(:districtCode AS String), '')), '%')) " +
            "AND (:wardCode IS NULL OR LOWER(r.user.address.ward.code) LIKE CONCAT('%', LOWER(COALESCE(CAST(:wardCode AS String), '')), '%')) " +
            "AND (:search IS NULL OR LOWER(r.title) LIKE CONCAT('%', LOWER(COALESCE(CAST(:search AS String), '')), '%')) " +
            "ORDER BY r.createDate DESC")
    Page<RequestEntity> findAllGivenRequest(@Param("type") boolean type,
                                            @Param("status") String status,
                                            Pageable pageable,
                                            @Param("provinceCode") Optional<String> provinceCode,
                                            @Param("districtCode") Optional<String> districtCode,
                                            @Param("wardCode") Optional<String> wardCode,
                                            @Param("search") Optional<String> search);
}
