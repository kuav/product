package ru.otpbank.service.template.application;

import org.springframework.stereotype.Service;
import ru.otpbank.service.template.application.dto.CreateTemplateCmd;
import ru.otpbank.service.template.domain.model.TemplateEntity;
import ru.otpbank.service.template.domain.repository.TemplateEntityRepository;

@Service
public class CreateTemplateUseCase {

    private TemplateEntityRepository templateEntityRepository;

    public TemplateEntity create(CreateTemplateCmd cmd) {
        return null;
    }
}
