package com.ilabservice.cloud.sdk.base.enums;

/**
 * @Author: jj.xue
 * @createTime: 2022年10月13日 09:30:00
 * @Description:
 */
public interface EnumBase {

    /**
     * 获取枚举名(建议与enumCode保持一致)
     *
     * @return
     */
    public String name();

    /**
     * 获取枚举消息
     *
     * @return
     */
    public String message();

}
