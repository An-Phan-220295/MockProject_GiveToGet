package com.mockproject.givetoget.repository;

import com.mockproject.givetoget.entity.UserInforEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInforEntity, Integer> {
}
