package ru.otpbank.prcat.service.product.domain.model;

import lombok.Builder;
import lombok.Getter;
import ru.otpbank.prcat.service.product.common.Validatable;
import ru.otpbank.prcat.service.product.common.validator.Unique;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Builder
@Unique(message = "{unique.tag.name}", fields = "name", entityClass = Tag.class, entityFields = "name")
public class CreateTagCmd implements Validatable {
    @NotNull(message = "{notnull.tag.name}")
    @Size(max = 512, message = "{size.tag.name}")
    private final String name;

    @Size(max = 512, message = "{size.tag.description}")
    private final String description;

    CreateTagCmd(String name, String description) {
        this.name = name;
        this.description = description;
        validate();
    }
}
