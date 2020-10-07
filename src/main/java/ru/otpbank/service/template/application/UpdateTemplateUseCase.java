package ru.otpbank.service.template.application;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import ru.otpbank.service.template.application.dto.UpdateTemplateCmd;
import ru.otpbank.service.template.domain.model.TemplateEntity;

@Service
@RequiredArgsConstructor
public class UpdateTemplateUseCase {

    private final ApplicationEventPublisher eventPublisher;

    public void update(UpdateTemplateCmd cmd) {
        //здесь должна быть логика обработки команды
        eventPublisher.publishEvent(new TemplateEntity(cmd.getId(), cmd.getName()));
    }
}
