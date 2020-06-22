package com.tansun.vos;

import lombok.Builder;
import lombok.Data;

/**
 * 租户Vo
 * @author linhb
 * @create 2019-08-06
 */
@Builder
@Data
public class TenantVo {
    /**
     * 租户id
     */
    private String id;
    /**
     * 租户名称
     */
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
