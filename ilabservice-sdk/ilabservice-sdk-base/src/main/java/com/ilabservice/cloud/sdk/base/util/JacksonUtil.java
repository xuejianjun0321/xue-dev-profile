package com.ilabservice.cloud.sdk.base.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description json 操作工具类：
 * @ClassName JacksonUtil
 * @Author xuejianjun
 * @date 2021.05.14 15:11
 */
@Slf4j
public class JacksonUtil {

    private final static ObjectMapper MAPPER = new ObjectMapper();

    static {
        // 未知字段忽略
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        MAPPER.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        MAPPER.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        MAPPER.registerModule(new JavaTimeModule());
        MAPPER.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    /**
     * 创建  jsonNode
     *
     * @return
     */
    public final static ObjectNode createObjectNode() {
        return MAPPER.createObjectNode();
    }

    /**
     * 创建 arraynode
     *
     * @return
     */
    public final static ArrayNode createArrayNode() {
        return MAPPER.createArrayNode();
    }

    /**
     * java 对象转换成 json 字符串
     *
     * @param input
     * @return
     */
    public final static String toJsonString(Object input) {
        try {
            return MAPPER.writeValueAsString(input);
        } catch (JsonProcessingException e) {
            log.warn("JacksonUtil 发生异常");
            return null;
        }
    }

    /**
     * object 转换成 json 支持 sring 字符，及 java 对象
     *
     * @param input
     * @return
     */
    public final static JsonNode toJsonNode(Object input) {
        return (input instanceof String) ? readTree((String) input) : valueToTree(input);
    }

    /**
     * json 字符串 转换成json 对象
     *
     * @param input
     * @return
     */
    public final static JsonNode readTree(String input) {
        if (input == null) {
            return null;
        }
        try {
            return MAPPER.readTree(input);
        } catch (JsonProcessingException e) {
            log.warn("JacksonUtil 发生异常");
            return null;
        }
    }

    /**
     * java 对象 转换的 json对象
     *
     * @param object
     * @return
     */
    public final static JsonNode valueToTree(Object object) {
        return MAPPER.valueToTree(object);
    }

    /**
     * string 字符串 转 java class 对象
     *
     * @param input
     * @param clazz
     * @param <T>
     */
    public final static <T> T stringToObject(String input, Class<T> clazz) {
        try {
            return MAPPER.readValue(input, clazz);
        } catch (JsonProcessingException e) {
            log.warn("JacksonUtil 发生异常");
            return null;
        }
    }

    /**
     * string 字符串 转成 java 对象
     *
     * @param input
     * @param tClass
     * @param <T>
     * @return
     */
    public final static <T> T parseObject(String input, Class<T> tClass) {
        return stringToObject(input, tClass);
    }

    /**
     * json 对象，转换 成java 对象
     *
     * @param input
     * @param tClass
     * @param <T>
     * @return
     */
    public final static <T> T parseObject(JsonNode input, Class<T> tClass) {
        return treeToValue(input, tClass);
    }

    /**
     * json 对象 转换成 class
     *
     * @param jsonNode
     * @param clazz
     * @param <T>
     * @return
     */
    public final static <T> T treeToValue(JsonNode jsonNode, Class<T> clazz) {
        try {
            return MAPPER.treeToValue(jsonNode, clazz);
        } catch (JsonProcessingException e) {
            log.warn("JacksonUtil 发生异常");
            return null;
        }
    }

    /**
     * json 对象转换成 java 对象 list
     *
     * @param jsonNode
     * @param tClass
     * @param <T>
     * @return
     */
    public final static <T> List<T> treeToListValue(JsonNode jsonNode, Class<T> tClass) {
        return (List<T>) treeToCollectionValues(jsonNode, List.class, tClass);
    }

    /**
     * json 对象 转换成 set
     *
     * @param jsonNode
     * @param tClass
     * @param <T>
     * @return
     */
    public final static <T> Set<T> treeToSetValue(JsonNode jsonNode, Class<T> tClass) {
        return (Set<T>) treeToCollectionValues(jsonNode, Set.class, tClass);
    }

    /**
     * json 对象转换成 map
     *
     * @param jsonNode
     * @param kClass
     * @param vClass
     * @param <K>
     * @param <V>
     * @return
     */
    public final static <K, V> Map<K, V> treeToMapValue(JsonNode jsonNode, Class<K> kClass,
                                                        Class<V> vClass) {
        JavaType javaType = MAPPER.getTypeFactory().constructMapType(Map.class, kClass, vClass);
        ObjectReader objectReader = MAPPER.readerFor(javaType);
        try {
            return objectReader.readValue(jsonNode);
        } catch (IOException e) {
            log.warn("JacksonUtil 发生异常");
            return null;
        }
    }

    /**
     * json 字符串转换成 map
     *
     * @param jsonStr
     * @param <K>
     * @param <V>
     * @return
     */
    public final static <K, V> Map<K, V> treeToMapValue(String jsonStr, Class<K> kClass,
                                                        Class<V> vClass) {
        if (jsonStr == null) {
            return null;
        }
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonStr);
            JavaType javaType = MAPPER.getTypeFactory().constructMapType(Map.class, kClass, vClass);
            ObjectReader objectReader = MAPPER.readerFor(javaType);
            return objectReader.readValue(jsonNode);
        } catch (IOException e) {
            log.warn("JacksonUtil 发生异常");
            return null;
        }
    }


    public final static <T> Collection<T> treeToCollectionValues(JsonNode jsonNode,
                                                                 Class<? extends Collection> collectionClazz, Class<T> tClass) {
        JavaType javaType = MAPPER.getTypeFactory().constructCollectionType(collectionClazz, tClass);
        ObjectReader objectReader = MAPPER.readerFor(javaType);
        try {
            return objectReader.readValue(jsonNode);
        } catch (IOException e) {
            log.warn("JacksonUtil 发生异常");
            return null;
        }
    }

    /**
     * json 对象 转换的 list<T>
     *
     * @param input
     * @param tClass
     * @param <T>
     * @return
     */
    public final static <T> List<T> parseList(JsonNode input, Class<T> tClass) {
        return treeToListValue(input, tClass);
    }

    /**
     * json 字符串，转换成 list
     *
     * @param input
     * @param tClass
     * @param <T>
     * @return
     */
    public final static <T> List<T> parseList(String input, Class<T> tClass) {
        JavaType javaType = MAPPER.getTypeFactory().constructCollectionType(List.class, tClass);
        ObjectReader objectReader = MAPPER.readerFor(javaType);
        try {
            if (StringUtils.isEmpty(input)) {
                return null;
            }
            return objectReader.readValue(input);
        } catch (JsonProcessingException e) {
            log.warn("JacksonUtil 发生异常");
            return null;
        }
    }

    /**
     * 判断 jsonnode  是否为 null
     *
     * @param jsonNode
     * @return
     */
    public final static boolean isNull(JsonNode jsonNode) {
        if (jsonNode == null) {
            return true;
        }
        return jsonNode.isNull();
    }

    /**
     * 判断jsonnode 是否存在
     *
     * @param jsonNode
     * @return
     */
    public final static boolean isMissingNode(JsonNode jsonNode) {
        if (jsonNode == null) {
            return true;
        }
        return jsonNode.isMissingNode();
    }

    /**
     * 判断 json node 是否 空数据
     *
     * @param jsonNode
     * @return
     */
    public final static boolean isEmpty(JsonNode jsonNode) {
        return isMissingNode(jsonNode) || isNull(jsonNode);
    }

    public final static <T> T readValue(String content, TypeReference<T> valueTypeRef) {
        try {
            return MAPPER.readValue(content, valueTypeRef);
        } catch (JsonProcessingException e) {
            log.warn("JacksonUtil 发生异常");
            return null;
        }
    }


    /**
     * 输出JSON日志
     *
     * @param input
     * @return
     */
    public static String toJsonStringForLog(Object input) {
        try {
            return MAPPER.writeValueAsString(input);
        } catch (Exception e) {
            log.error("对象转化为 JSON 异常", e);
            return "";
        }
    }
}

