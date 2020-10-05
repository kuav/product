package ru.otpbank.service.template.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otpbank.service.template.application.dto.UpdateTemplateCmd;
import ru.otpbank.service.template.domain.repository.TemplateEntityRepository;

@Service
@RequiredArgsConstructor
public class UpdateTemplateUseCase {

    private final TemplateEntityRepository templateRepository;

    public void update(UpdateTemplateCmd cmd) {

    }
}
