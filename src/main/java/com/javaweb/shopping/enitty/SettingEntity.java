package com.javaweb.shopping.enitty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Setting")
public class SettingEntity extends BaseEntity<UserEntity> {
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
