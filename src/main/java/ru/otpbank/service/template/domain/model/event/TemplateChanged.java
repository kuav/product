package ru.otpbank.service.template.domain.model.event;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TemplateChanged {

    private final String id;

    private final String name;
}
