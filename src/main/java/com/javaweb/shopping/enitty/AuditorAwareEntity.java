package com.javaweb.shopping.enitty;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareEntity implements AuditorAware<UserEntity> {
    @Override
    public Optional<UserEntity> getCurrentAuditor() {
        UserEntity user = new UserEntity("le huy tuan", "tuandz", "tuandz", 1);
        return Optional.of(user);
    }
}
