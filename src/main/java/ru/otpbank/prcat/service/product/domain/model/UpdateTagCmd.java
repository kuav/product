package ru.otpbank.prcat.service.product.domain.model;

import lombok.Builder;
import lombok.Getter;
import ru.otpbank.prcat.service.product.common.Validatable;
import ru.otpbank.prcat.service.product.common.validator.Unique;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Builder
@Unique(message = "{unique.tag.name}",
        idField = "id",
        fields = {"name"},
        entityClass = Tag.class,
        entityFields = {"name"})
public class UpdateTagCmd implements Validatable {
    @NotNull(message = "{notnull.tag.id}")
    private final String id;

    @NotNull(message = "{notnull.tag.name}")
    @Size(max = 512, message = "{size.tag.name}")
    private final String name;

    @Size(max = 512, message = "{size.tag.description}")
    private final String description;

    UpdateTagCmd(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        validate();
    }
}
