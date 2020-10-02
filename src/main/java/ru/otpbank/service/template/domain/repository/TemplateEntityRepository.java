package ru.otpbank.service.template.domain.repository;

import ru.otpbank.service.template.domain.model.TemplateEntity;

import java.util.Optional;

public interface TemplateEntityRepository {

    Optional<TemplateEntity> findById(String id);
}
