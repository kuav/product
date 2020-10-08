package ru.otpbank.service.template.domain.model;

import lombok.Getter;

@Getter
public class TemplateEntity {

    private String id;

    private String name;

    public TemplateEntity(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public void updateName(String name) {
        this.name = name;
    }
}
