package com.login.login_service.service;

import com.login.login_service.dto.responseDto.MyUserDetailDto;
import com.login.login_service.entity.UserEntity;
import com.login.login_service.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {
    private final UserRepo userRepo;
  @Autowired
    public MyUserDetailService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity= this.userRepo.findByUserName(username);
        if(userEntity==null){
            throw  new UsernameNotFoundException("user name not found");
        }
        else{
            return new MyUserDetailDto(userEntity);
        }
    }
}
