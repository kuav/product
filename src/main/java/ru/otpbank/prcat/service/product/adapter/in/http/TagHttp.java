package ru.otpbank.prcat.service.product.adapter.in.http;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TagHttp {
    private String id;
    private String fullName;
    private String shortName;
    private String description;
}
