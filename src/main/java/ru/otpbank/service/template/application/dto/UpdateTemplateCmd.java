package ru.otpbank.service.template.application.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateTemplateCmd {

    private final String id;

    private final String name;
}
