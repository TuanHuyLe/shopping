package com.javaweb.shopping.entity;

import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Where(clause = "is_active=1")
@Table(name = "Setting")
public class SettingEntity extends BaseEntity<String> {
    @Column(length = 191)
    private String configKey;
    @Column(columnDefinition = "TEXT")
    private String configValue;
    @Column(length = 191)
    private String type;

    //getter and setter
    public String getConfigKey() {
        return configKey;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public SettingEntity(String configKey, String configValue, String type) {
        this.configKey = configKey;
        this.configValue = configValue;
        this.type = type;
    }

    public SettingEntity() {
    }
}
