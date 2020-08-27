package com.javaweb.shopping.configuration;

import com.javaweb.shopping.enitty.AuditorAwareEntity;
import com.javaweb.shopping.enitty.UserEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class AuditorConfiguration {
    @Bean
    public AuditorAware<UserEntity> auditorAware() {
        return new AuditorAwareEntity();
    }
}
