package com.ilabservice.cloud.sdk.base.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * @Author: jj.xue
 * @createTime: 2022年11月22日 11:01:06
 * @Description: 组件类枚举
 */
@Getter
@AllArgsConstructor
public enum ComponentTypeEnum {


    /**
     * 未知选项
     */
    UNKNOWN("UNKNOWN", "未知选项"),

    TABLE("TABLE", "表"),

    COMPONENT("COMPONENT", "组件"),

    ;

    private String operation;
    private String describe;


    /**
     * 根据Key得到枚举的Value
     *
     * @param value
     * @return enum
     */
    public static ComponentTypeEnum getEnumType(Integer value) {
        ComponentTypeEnum[] alarmGrades = ComponentTypeEnum.values();
        ComponentTypeEnum result = Arrays.asList(alarmGrades).stream()
                .filter(alarmGrade -> alarmGrade.getOperation().equals(value))
                .findFirst().orElse(ComponentTypeEnum.UNKNOWN);
        return result;
    }

    /**
     * 根据Value得到枚举的Key
     *
     * @param describe
     * @return Integer
     */
    public static String getEnumKey(String describe) {
        ComponentTypeEnum[] alarmGrades = ComponentTypeEnum.values();
        ComponentTypeEnum result = Arrays.asList(alarmGrades).stream()
                .filter(alarmGrade -> alarmGrade.getDescribe().equals(describe))
                .findFirst().orElse(ComponentTypeEnum.UNKNOWN);
        return result.getOperation();
    }
}
