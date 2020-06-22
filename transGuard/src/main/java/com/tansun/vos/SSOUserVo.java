package com.tansun.vos;

import lombok.Builder;

import java.io.Serializable;

/**
 * 单点登陆用户
 * @author linhb
 * @create 2019-08-06
 */
@Builder
public class SSOUserVo implements Serializable{
    /**
     * 子帐号ID
     */
    private int id ;
    /**
     * 子帐号登录名
     */
    private String name;
    /**
     * 子帐号中文名
     */
    private String alias;
    /**
     * 子帐号手机号码
     */
    private String phone;
    /**
     * 子帐号邮箱
     */
    private String email;
    /**
     * 子帐号所在分组列表
     */
    private String groups;
    /**
     * 是否是租户
     */
    private boolean isTenant;
    /**
     * 所属租户信息
     */
    private TenantVo tenant;

    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 最后更新时间
     */
    private String updateTime;

    public boolean getIsTenant() {
        return isTenant;
    }

    public void setIsTenant(boolean isTenant) {
        isTenant = isTenant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }

    public TenantVo getTenant() {
        return tenant;
    }

    public void setTenant(TenantVo tenant) {
        this.tenant = tenant;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
