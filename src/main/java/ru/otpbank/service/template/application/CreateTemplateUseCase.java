package ru.otpbank.service.template.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otpbank.service.template.application.dto.CreateTemplateCmd;
import ru.otpbank.service.template.domain.model.TemplateEntity;
import ru.otpbank.service.template.domain.repository.TemplateEntityRepository;

@Service
@RequiredArgsConstructor
public class CreateTemplateUseCase {

    private final TemplateEntityRepository templateRepository;

    public TemplateEntity create(CreateTemplateCmd cmd) {
        return null;
    }
}
