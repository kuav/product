package ru.otpbank.template.adapter.out.postgresql;

import ru.otpbank.template.domain.model.TemplateEntity;
import ru.otpbank.template.domain.repository.TemplateEntityRepository;

import java.util.Optional;

class PostgreSqlTemplateEntityRepository implements TemplateEntityRepository {

    @Override
    public Optional<TemplateEntity> findById(String id) {
        return Optional.empty();
    }
}
