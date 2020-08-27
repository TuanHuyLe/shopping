package com.javaweb.shopping.configuration;

import com.javaweb.shopping.entity.AuditorAwareEntity;
import com.javaweb.shopping.entity.UserEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class AuditorConfiguration {
    @Bean
    public AuditorAware<String> auditorAware() {
        return new AuditorAwareEntity();
    }
}
