package ru.otpbank.service.product.adapter.in.http;

import lombok.Data;

@Data
public class TagHttp {
    private String id;
    private String fullName;
    private String shortName;
    private String description;
}
