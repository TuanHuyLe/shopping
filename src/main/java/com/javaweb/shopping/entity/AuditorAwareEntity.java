package com.javaweb.shopping.entity;

import com.javaweb.shopping.repository.IUserRepository;
import com.javaweb.shopping.service.impl.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class AuditorAwareEntity implements AuditorAware<String> {
    @Autowired
    private IUserRepository userRepository;

    private String getCurrentUser(Authentication authentication) {
        if ("anonymousUser".equals(authentication.getPrincipal())) {
            return "admin-shopping";
        }
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        return userRepository
                .findById(userPrincipal.getId())
                .orElseThrow(() -> new UsernameNotFoundException("User name not found"))
                .getUsername();
    }

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return Optional.of(getCurrentUser(auth));
    }
}
