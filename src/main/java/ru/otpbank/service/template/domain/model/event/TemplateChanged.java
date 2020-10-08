package ru.otpbank.service.template.domain.model.event;

import lombok.Value;

@Value
public class TemplateChanged {

    private String id;

    private String name;
}
