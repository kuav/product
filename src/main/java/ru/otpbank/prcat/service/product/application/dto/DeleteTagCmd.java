package ru.otpbank.prcat.service.product.application.dto;

import lombok.Builder;
import lombok.Getter;
import ru.otpbank.prcat.service.product.common.Validatable;

import javax.validation.constraints.NotNull;

@Getter
@Builder
public class DeleteTagCmd implements Validatable {
    @NotNull(message = "{notnull.tag.id}")
    private final String id;

    DeleteTagCmd(String id) {
        this.id = id;
        validate();
    }
}
