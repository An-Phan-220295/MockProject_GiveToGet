package com.mockproject.givetoget.repository;

import com.mockproject.givetoget.entity.RequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<RequestEntity, Integer> {
    @Query("select r from RequestEntity r where r.type = :type and r.status.status = :status order by r.createDate desc")
    List<RequestEntity> findAllGivenRequest(@Param("type") boolean type, @Param("status") String status);

}
