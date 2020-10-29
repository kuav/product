package ru.otpbank.prcat.service.product.domain.model;

import lombok.Builder;
import lombok.Getter;
import ru.otpbank.prcat.service.product.common.Validatable;
import ru.otpbank.prcat.service.product.common.validator.Unique;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Builder
@Unique(message = "Tag with same fullName is already exists", fields = "fullName", entityClass = Tag.class, entityFields = "fullName")
public class CreateTagCmd implements Validatable {
    @NotNull(message = "{notnull.tag.fullname}")
    @Size(max = 512, message = "{size.tag.fullname}")
    private final String fullName;

    @Size(max = 512, message = "{size.tag.shortname}")
    private final String shortName;

    @Size(max = 512, message = "{size.tag.description}")
    private final String description;

    CreateTagCmd(String fullName, String shortName, String description) {
        this.fullName = fullName;
        this.shortName = shortName;
        this.description = description;
        validate();
    }
}
