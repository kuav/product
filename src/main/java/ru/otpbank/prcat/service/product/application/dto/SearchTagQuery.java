package ru.otpbank.prcat.service.product.application.dto;

import lombok.Builder;
import lombok.Getter;
import ru.otpbank.prcat.service.product.common.Validatable;

import javax.validation.constraints.NotEmpty;

@Getter
@Builder
public class SearchTagQuery implements Validatable {

    @NotEmpty
    private final String id;

    public SearchTagQuery(String id) {
        this.id = id;
        validate();
    }
}
