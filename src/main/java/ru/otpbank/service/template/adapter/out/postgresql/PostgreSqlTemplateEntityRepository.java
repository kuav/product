package ru.otpbank.service.template.adapter.out.postgresql;

import org.springframework.stereotype.Repository;
import ru.otpbank.service.template.domain.model.TemplateEntity;
import ru.otpbank.service.template.domain.repository.TemplateEntityRepository;

import java.util.Optional;

@Repository
class PostgreSqlTemplateEntityRepository implements TemplateEntityRepository {

    @Override
    public Optional<TemplateEntity> findById(String id) {
        return Optional.empty();
    }
}
