package com.login.login_service.repository;

import com.login.login_service.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserEntity,Long> {
    UserEntity findByUserName(String userName);

}
