package com.qualityobjects.commons.utils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonUtils {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final ObjectMapper MAPPER_PRETTY;

    private static final String ERROR_JSON = "Error formating JSON from object: {} ";

    static {

        MAPPER.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        MAPPER.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        MAPPER.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        MAPPER.disable(SerializationFeature.INDENT_OUTPUT);
        MAPPER.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        MAPPER.registerModule(new JavaTimeModule());
        MAPPER.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
        MAPPER_PRETTY = MAPPER.copy();
        MAPPER_PRETTY.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public static Map<String, Object> toMap(JsonNode jsonNode) {

        return MAPPER.convertValue(jsonNode, new TypeReference<Map<String, Object>>() {
        });
    }

    /**
     * Create a JSON string from a object compatible or annotated with Jackson, i.e:
     * <code>
     * {"f1":2345,"f2":"Test de valor"}
     * </code>
     *
     * @param obj
     * @return JSON string representation from object
     */
    public static String toPrettyJSON(Object obj, Class<?> klass) throws IOException {
        // and could also do other configuration...
        if (obj == null)
            return null;
        return MAPPER_PRETTY.writerFor(klass).writeValueAsString(obj);
    }

    public static String toPrettyJSON(Object obj) throws IOException {
        if (obj == null)
            return null;
        return MAPPER_PRETTY.writeValueAsString(obj);
    }

    public static <T> T parseJSON(String json, Class<T> type) throws IOException {

        return parseJSON(json, new TypeReference<T>() {
            @Override
            public Type getType() {
                return type;
            }
        });
    }

    public static <T> T parseJSON(String json, JavaType javaType) throws IOException {
        try {
            if (json == null)
                return null;
            return MAPPER.readValue(json, javaType);
        } catch (JsonParseException e) {
            if (json.length() > 60) {
                json = json.substring(0, 50) + "...";
            }
            throw new IOException(ERROR_JSON + json, e);
        }
    }

    public static <T> T parseJSON(String json, TypeReference<T> type) throws IOException {
        try {
            if (json == null)
                return null;
            return MAPPER.readValue(json, type);
        } catch (JsonParseException e) {
            if (json.length() > 60) {
                json = json.substring(0, 50) + "...";
            }
            throw new IOException(ERROR_JSON + json, e);
        }
    }

    /**
     * Create a JSON string from a object compatible or annotated with Jackson, i.e:
     * <code>
     * {"f1":2345,"f2":"Test de valor"}
     * </code>
     *
     * @param obj
     * @return JSON string representation from object
     */
    public static String toJSON(Object obj) throws IOException {
        if (obj == null) {
            return null;
        }
        return MAPPER.writeValueAsString(obj);
    }

    public static final Pattern CAMEL_2_SNAKE_PATTERN = Pattern.compile("(?<!^)(?=[A-Z])");

    public static String toSnakeCase(String name) {
        Matcher matcher = CAMEL_2_SNAKE_PATTERN.matcher(name);

        return matcher.replaceAll("_").toLowerCase();
    }
}
