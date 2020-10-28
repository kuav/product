package ru.otpbank.prcat.service.product.common;

import java.util.UUID;

public final class CommonUtils {

    public static String generateUUID(){
        return UUID.randomUUID().toString();
    }
}
