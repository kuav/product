package ru.otpbank.service.template.application.dto;

import lombok.Getter;
import ru.otpbank.service.template.common.CommonUtils;
import ru.otpbank.service.template.common.Validatable;

@Getter
public class CreateTemplateCmd implements Validatable {

    private final String name;

    public CreateTemplateCmd(String name) {
        this.name = CommonUtils.trimUpperOrNull(name);
        validate();
    }
}
