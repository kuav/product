package ru.otpbank.prcat.service.product.adapter.in.http;

import ru.otpbank.prcat.service.product.model.TagHttp;

public class TagMother {

    public static final String ESTATE_NAME = "Страхование имущества";
    public static final String ESTATE_DESCRIPTION = "Страхование имущества описание";

    public static TagHttp.TagHttpBuilder avto() {
        return TagHttp.builder()
                .name("Автострахование")
                .description("Страхование автомобилей");
    }

    public static TagHttp.TagHttpBuilder empty() {
        return TagHttp.builder();
    }

    public static TagHttp.TagHttpBuilder estate() {
        return TagHttp.builder()
                .name(ESTATE_NAME)
                .description(ESTATE_DESCRIPTION);
    }


}
