package com.hcy.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.List;

/**
 * Description：
 * Author: 黄成勇
 * Date:  2022/2/13 19:12
 */
@TableName("credentials")
public class Credentials {
    @TableId(value = "id")
    private Integer id;
    @TableField(value = "enabled")
    private Integer enabled;
    @TableField(value = "name")
    private String name;
    @TableField(value = "password")
    private String password;
    @TableField(value = "version")
    private Integer version;

    private List<String> authorities;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String toString() {
        return "Credentials{" +
                "id=" + id +
                ", enabled=" + enabled +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", version=" + version +
                ", authorities=" + authorities +
                '}';
    }
}
