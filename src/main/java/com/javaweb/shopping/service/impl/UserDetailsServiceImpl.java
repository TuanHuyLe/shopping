package com.javaweb.shopping.service.impl;

import com.javaweb.shopping.entity.UserEntity;
import com.javaweb.shopping.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    IUserRepository userRepository;

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(name)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "User not found with username: " + name
                ));
        return UserDetailsImpl.build(user);
    }
}
