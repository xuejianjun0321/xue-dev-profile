package com.ilabservice.cloud.sdk.base.enums;

/**
 * @Author: jj.xue
 * @createTime: 2022年11月22日 09:44:06
 * @Description:
 */

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * @ClassName: FlagEnum
 * @Description: 标识枚举
 * @Author: jj.xue
 * @Data: 2022年11月16日 15:33:00
 **/
@Getter
@AllArgsConstructor
public enum FlagEnum {

    /**
     * 未知选项
     */
    UNKNOWN(-1, "未知选项"),

    YES(1, "是"),

    NO(0, "否"),

    ;

    private Integer operation;
    private String describe;


    /**
     * 根据Key得到枚举的Value
     *
     * @param value
     * @return enum
     */
    public static FlagEnum getEnumType(Integer value) {
        FlagEnum[] alarmGrades = FlagEnum.values();
        FlagEnum result = Arrays.asList(alarmGrades).stream()
                .filter(alarmGrade -> alarmGrade.getOperation().equals(value))
                .findFirst().orElse(FlagEnum.UNKNOWN);
        return result;
    }

    /**
     * 根据Value得到枚举的Key
     *
     * @param describe
     * @return Integer
     */
    public static Integer getEnumKey(String describe) {
        FlagEnum[] alarmGrades = FlagEnum.values();
        FlagEnum result = Arrays.asList(alarmGrades).stream()
                .filter(alarmGrade -> alarmGrade.getDescribe().equals(describe))
                .findFirst().orElse(FlagEnum.UNKNOWN);
        return result.getOperation();
    }

}
