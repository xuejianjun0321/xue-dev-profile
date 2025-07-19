package com.ilabservice.cloud.sdk.base.util;


import java.util.Collection;

import com.ilabservice.cloud.sdk.base.enums.CommonResultEnum;
import com.ilabservice.cloud.sdk.base.exception.AutoBaseBizRuntimeException;
import com.ilabservice.cloud.sdk.base.exception.RestServiceException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

/**
 * @Description 断言工具类
 * @ClassName AssertUtil
 * @Author xuejianjun
 * @date 2021.05.18 13:52
 */
public class AssertUtil {

    /**
     * 断言表达式的值为true，否则抛RestServiceException
     *
     * @param expValue 断言表达式的值
     * @param errCode  异常错误码
     * @throws AutoBaseBizRuntimeException e
     */
    public static void assertTrue(boolean expValue, CommonResultEnum errCode)
            throws AutoBaseBizRuntimeException {
        if (!expValue) {
            throw new AutoBaseBizRuntimeException(errCode.getMessage());
        }
    }

    /**
     * 断言表达式的值为true，否则抛RestServiceException
     *
     * @param expValue 断言表达式的值
     * @param errCode  异常错误码
     * @throws AutoBaseBizRuntimeException e
     */
    public static void assertTrueWarn(boolean expValue, CommonResultEnum errCode)
            throws RestServiceException {
        if (!expValue) {
            throw new RestServiceException(errCode.getCode(),errCode.getMessage());
        }
    }

    /**
     * 断言表达式的值为true，否则抛RestServiceException
     *
     * @param expValue 断言表达式的值
     * @param errCode  异常错误码
     * @param errMsg   异常描述
     * @throws AutoBaseBizRuntimeException e
     */
    public static void assertTrue(boolean expValue, CommonResultEnum errCode,
                                  String errMsg) throws AutoBaseBizRuntimeException {
        if (!expValue) {
            throw new AutoBaseBizRuntimeException(String.valueOf(errCode.getCode()), errMsg);
        }
    }

    /**
     * 断言表达式的值为true，否则抛RestServiceException
     *
     * @param expValue 断言表达式的值
     * @param errCode  异常错误码
     * @param errMsg   异常描述
     * @throws RestServiceException e
     */
    public static void assertTrueWarn(boolean expValue, CommonResultEnum errCode,
                                      String errMsg) throws RestServiceException {
        if (!expValue) {
            throw new RestServiceException(errCode.getCode(), errMsg);
        }
    }

    /**
     * 断言表达式的值为 false，否则抛RestServiceException
     *
     * @param expValue 断言表达式的值
     * @param errCode  异常错误码
     * @throws AutoBaseBizRuntimeException e
     */
    public static void assertFalseWarn(boolean expValue, CommonResultEnum errCode)
            throws AutoBaseBizRuntimeException {
        if (expValue) {
            throw new RestServiceException(errCode.getCode(),errCode.getMessage());
        }
    }

    /**
     * 断言表达式的值为false，否则抛RestServiceException
     *
     * @param expValue 断言表达式的值
     * @param errCode  异常错误码
     * @param errMsg   异常描述
     * @throws AutoBaseBizRuntimeException e
     */
    public static void assertFalseWarn(boolean expValue, CommonResultEnum errCode,
                                       String errMsg) throws AutoBaseBizRuntimeException {
        if (expValue) {
            throw new RestServiceException(errCode.getCode(), errMsg);
        }
    }

    /**
     * 断言表达式抛异常 不会报警
     *
     * @param errCode  异常错误码
     * @throws AutoBaseBizRuntimeException e
     */
    public static void assertWarn(CommonResultEnum errCode)
            throws AutoBaseBizRuntimeException {
        throw new RestServiceException(errCode.getMessage());
    }


    /**
     * 断言表达式的抛异常，会报警
     *
     * @param errCode  异常错误码
     * @throws AutoBaseBizRuntimeException e
     */
    public static void assertError(CommonResultEnum errCode)
            throws AutoBaseBizRuntimeException {
        throw new AutoBaseBizRuntimeException(errCode.getMessage());
    }

    /**
     * 断言表达式的值为 false，否则抛RestServiceException
     *
     * @param expValue 断言表达式的值
     * @param errCode  异常错误码
     * @throws AutoBaseBizRuntimeException e
     */
    public static void assertFalse(boolean expValue, CommonResultEnum errCode)
            throws AutoBaseBizRuntimeException {
        if (expValue) {
            throw new AutoBaseBizRuntimeException(errCode.getMessage());
        }
    }

    /**
     * 断言表达式的值为false，否则抛RestServiceException
     *
     * @param expValue 断言表达式的值
     * @param errCode  异常错误码
     * @param errMsg   异常描述
     * @throws AutoBaseBizRuntimeException e
     */
    public static void assertFalse(boolean expValue, CommonResultEnum errCode,
                                   String errMsg) throws AutoBaseBizRuntimeException {
        if (expValue) {
            throw new AutoBaseBizRuntimeException(String.valueOf(errCode.getCode()), errMsg);
        }
    }


    /**
     * 断言两个对象相等，否则抛RestServiceException
     *
     * @param obj1    A对象
     * @param obj2    B对象
     * @param errCode 异常错误码
     * @throws AutoBaseBizRuntimeException e
     */
    public static void assertEquals(Object obj1, Object obj2,
                                    CommonResultEnum errCode) throws AutoBaseBizRuntimeException {
        if (obj1 == null) {
            assertNull(obj2, errCode);
            return;
        }

        if (!obj1.equals(obj2)) {
            throw new AutoBaseBizRuntimeException(errCode.getMessage());
        }
    }


    /**
     * 断言两个对象相等，否则抛RestServiceException
     *
     * @param obj1    A对象
     * @param obj2    B对象
     * @param errCode 异常错误码
     * @param errMsg  异常描述
     * @throws AutoBaseBizRuntimeException e
     */
    public static void assertEquals(Object obj1, Object obj2, CommonResultEnum errCode,
                                    String errMsg) throws AutoBaseBizRuntimeException {
        if (obj1 == null) {
            assertNull(obj2, errCode, errMsg);
            return;
        }

        if (!obj1.equals(obj2)) {
            throw new AutoBaseBizRuntimeException(String.valueOf(errCode.getCode()), errMsg);
        }
    }


    /**
     * 断言两个对象不等，否则抛RestServiceException
     *
     * @param obj1    A对象
     * @param obj2    B对象
     * @param errCode 异常错误码
     * @throws AutoBaseBizRuntimeException e
     */
    public static void assertNotEquals(Object obj1, Object obj2, CommonResultEnum errCode)
            throws AutoBaseBizRuntimeException {
        if (obj1 == null) {
            assertNotNull(obj2, errCode);
            return;
        }

        if (obj1.equals(obj2)) {
            throw new AutoBaseBizRuntimeException(errCode.getMessage());
        }
    }


    /**
     * 断言两个对象不等，否则抛RestServiceException
     *
     * @param obj1    A对象
     * @param obj2    B对象
     * @param errCode 异常错误码
     * @param errMsg  异常描述
     * @throws AutoBaseBizRuntimeException e
     */
    public static void assertNotEquals(Object obj1, Object obj2, CommonResultEnum errCode,
                                       String errMsg) throws AutoBaseBizRuntimeException {
        if (obj1 == null) {
            assertNotNull(obj2, errCode, errMsg);
            return;
        }

        if (obj1.equals(obj2)) {
            throw new AutoBaseBizRuntimeException(String.valueOf(errCode.getCode()), errMsg);
        }
    }


    /**
     * 断言对象至少等于容器中的一个
     *
     * @param obj1    断言对象
     * @param objects 断言对象所在容器
     * @param errCode 异常错误码
     * @param errMsg  异常描述
     * @throws AutoBaseBizRuntimeException e
     */
    public static void assertEqualsAny(Object obj1, Object[] objects, CommonResultEnum errCode,
                                       String errMsg) throws AutoBaseBizRuntimeException {
        if (null == objects) {
            throw new AutoBaseBizRuntimeException(String.valueOf(errCode.getCode()), errMsg);
        }

        for (Object obj2 : objects) {
            if (obj1 == null) {
                if (obj2 == null) {
                    return;
                }
                continue;
            }

            if (obj1.equals(obj2)) {
                return;
            }
        }

        throw new AutoBaseBizRuntimeException(String.valueOf(errCode.getCode()), errMsg);
    }


    /**
     * 断言两个对象相同，否则抛RestServiceException
     *
     * @param obj1    A对象
     * @param obj2    B对象
     * @param errCode 异常错误码
     * @param errMsg  异常描述
     * @throws AutoBaseBizRuntimeException e
     */
    public static void assertIs(Object obj1, Object obj2, CommonResultEnum errCode,
                                String errMsg) throws AutoBaseBizRuntimeException {
        if (obj1 != obj2) {
            throw new AutoBaseBizRuntimeException(String.valueOf(errCode.getCode()), errMsg);
        }
    }


    /**
     * 断言两个对象不同，否则抛RestServiceException
     *
     * @param obj1    A对象
     * @param obj2    B对象
     * @param errCode 异常错误码
     * @param errMsg  异常描述
     * @throws AutoBaseBizRuntimeException e
     */
    public static void assertIsNot(Object obj1, Object obj2, CommonResultEnum errCode,
                                   String errMsg) throws AutoBaseBizRuntimeException {
        if (obj1 == obj2) {
            throw new AutoBaseBizRuntimeException(String.valueOf(errCode.getCode()), errMsg);
        }
    }

    /**
     * 断言对象在容器中
     *
     * @param obj1    断言对象
     * @param objs    断言对象所在容器
     * @param errCode 异常错误码
     * @param errMsg  异常描述
     * @throws AutoBaseBizRuntimeException e
     */
    public static void assertIn(Object obj1, Object[] objs, CommonResultEnum errCode,
                                String errMsg) throws AutoBaseBizRuntimeException {
        if (null == objs) {
            throw new AutoBaseBizRuntimeException(String.valueOf(errCode.getCode()), errMsg);
        }

        for (Object obj : objs) {
            if (obj == obj1) {
                return;
            }
        }

        throw new AutoBaseBizRuntimeException(String.valueOf(errCode.getCode()), errMsg);
    }


    /**
     * 断言字符串为空，否则抛RestServiceException
     *
     * @param str     断言字符串
     * @param errCode 异常错误码
     * @param errMsg  异常描述
     * @throws AutoBaseBizRuntimeException e
     */
    public static void assertEmpty(String str, CommonResultEnum errCode,
                                   String errMsg) throws AutoBaseBizRuntimeException {
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(str)) {
            throw new AutoBaseBizRuntimeException(String.valueOf(errCode.getCode()), errMsg);
        }
    }


    /**
     * 断言字符串为非空，否则抛RestServiceException
     *
     * @param str     断言字符串
     * @param errCode 异常错误码
     * @param errMsg  异常描述
     * @throws AutoBaseBizRuntimeException e
     */
    public static void assertNotEmpty(String str, CommonResultEnum errCode,
                                      String errMsg) throws AutoBaseBizRuntimeException {
        if (org.apache.commons.lang3.StringUtils.isEmpty(str)) {
            throw new AutoBaseBizRuntimeException(String.valueOf(errCode.getCode()), errMsg);
        }
    }


    /**
     * 断言字符串为空，否则抛RestServiceException
     *
     * @param str     断言字符串
     * @param errCode 异常错误码
     * @param errMsg  异常描述
     * @throws AutoBaseBizRuntimeException e
     */
    public static void assertBlank(String str, CommonResultEnum errCode,
                                   String errMsg) throws AutoBaseBizRuntimeException {
        if (org.apache.commons.lang3.StringUtils.isNotBlank(str)) {
            throw new AutoBaseBizRuntimeException(String.valueOf(errCode.getCode()), errMsg);
        }
    }


    /**
     * 断言字符串为非空，否则抛RestServiceException
     *
     * @param str     断言字符串
     * @param errCode 异常错误码
     * @throws AutoBaseBizRuntimeException e
     */
    public static void assertNotBlank(String str, CommonResultEnum errCode)
            throws AutoBaseBizRuntimeException {
        if (org.apache.commons.lang3.StringUtils.isBlank(str)) {
            throw new AutoBaseBizRuntimeException(errCode.getMessage());
        }
    }

    /**
     * 断言字符串为非空，否则抛RestServiceException
     *
     * @param str     断言字符串
     * @param errCode 异常错误码
     * @param errMsg  异常描述
     * @throws AutoBaseBizRuntimeException e
     */
    public static void assertNotBlank(String str, CommonResultEnum errCode,
                                      String errMsg) throws AutoBaseBizRuntimeException {
        if (StringUtils.isBlank(str)) {
            throw new AutoBaseBizRuntimeException(String.valueOf(errCode.getCode()), errMsg);
        }
    }


    /**
     * 断言对象为null，否则抛RestServiceException
     *
     * @param object  断言对象
     * @param errCode 异常错误码
     * @throws AutoBaseBizRuntimeException e
     */
    public static void assertNull(Object object, CommonResultEnum errCode)
            throws AutoBaseBizRuntimeException {
        if (object != null) {
            throw new AutoBaseBizRuntimeException(errCode.getMessage());
        }
    }

    /**
     * 断言对象为null，否则抛RestServiceException
     *
     * @param object  断言对象
     * @param errCode 异常错误码
     * @param errMsg  异常描述
     * @throws AutoBaseBizRuntimeException e
     */
    public static void assertNull(Object object, CommonResultEnum errCode,
                                  String errMsg) throws AutoBaseBizRuntimeException {
        if (object != null) {
            throw new AutoBaseBizRuntimeException(errCode.getMessage(), errMsg);
        }
    }


    /**
     * 断言对象非null，否则抛RestServiceException
     *
     * @param object  断言对象
     * @param errCode 异常错误码
     * @param errMsg  异常描述
     * @throws AutoBaseBizRuntimeException e
     */
    public static void assertNotNull(Object object, CommonResultEnum errCode,
                                     String errMsg) throws AutoBaseBizRuntimeException {
        if (null == object) {
            throw new AutoBaseBizRuntimeException(String.valueOf(errCode.getCode()), errMsg);
        }
    }


    /**
     * 断言对象非null，否则抛RestServiceException
     *
     * @param object  断言对象
     * @param errCode 异常错误码
     * @throws AutoBaseBizRuntimeException e
     */
    public static void assertNotNull(Object object, CommonResultEnum errCode)
            throws AutoBaseBizRuntimeException {
        if (null == object) {
            throw new AutoBaseBizRuntimeException(errCode.getMessage());
        }
    }

    /**
     * 断言对象非null，否则抛IllegalArgumentException
     *
     * @param object  断言对象
     * @param errCode 异常错误码
     * @throws AutoBaseBizRuntimeException e
     */
    public static void assertNotNullWarn(Object object, CommonResultEnum errCode)
            throws IllegalArgumentException {
        if (null == object) {
            throw new IllegalArgumentException(errCode.getMessage());
        }
    }

    /**
     * 断言集合不为空或null，否则抛RestServiceException
     *
     * @param collection 断言集合
     * @param errCode    异常错误码
     * @param errMsg     异常描述
     * @throws AutoBaseBizRuntimeException e
     */
    @SuppressWarnings("rawtypes")
    public static void assertNotBlank(Collection collection, CommonResultEnum errCode,
                                      String errMsg) throws AutoBaseBizRuntimeException {
        if (CollectionUtils.isEmpty(collection)) {
            throw new AutoBaseBizRuntimeException(String.valueOf(errCode.getCode()), errMsg);
        }
    }


}