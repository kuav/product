package ru.otpbank.prcat.service.product.common;

import org.springframework.data.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.UUID;

public final class CommonUtils {

    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }

    public static Object getValue(Object object, String fieldName) throws IllegalAccessException {
        Field field = ReflectionUtils.findRequiredField(object.getClass(), fieldName);
        field.setAccessible(true);
        Object value = field.get(object);
        return value;
    }
}
