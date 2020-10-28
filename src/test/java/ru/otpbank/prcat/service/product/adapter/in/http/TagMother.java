package ru.otpbank.prcat.service.product.adapter.in.http;

public class TagMother {

    public static final String ESTATE_FULLNAME = "Страхование имущества";
    public static final String ESTATE_SHORTNAME = "Имущество";
    public static final String ESTATE_DESCRIPTION = "Страхование имущества описание";

    public static TagHttp.TagHttpBuilder avto() {
        return TagHttp.builder()
                .fullName("Автострахование")
                .shortName("Авто")
                .description("Страхование автомобилей");
    }

    public static TagHttp.TagHttpBuilder empty() {
        return TagHttp.builder();
    }

    public static TagHttp.TagHttpBuilder estate() {
        return TagHttp.builder()
                .fullName(ESTATE_FULLNAME)
                .shortName(ESTATE_SHORTNAME)
                .description(ESTATE_DESCRIPTION);
    }


}
