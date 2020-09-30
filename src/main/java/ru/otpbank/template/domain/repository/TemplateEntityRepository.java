package ru.otpbank.template.domain.repository;

import ru.otpbank.template.domain.model.TemplateEntity;

import java.util.Optional;

public interface TemplateEntityRepository {

    Optional<TemplateEntity> findById(String id);
}
