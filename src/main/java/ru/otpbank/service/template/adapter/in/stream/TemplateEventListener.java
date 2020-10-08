package ru.otpbank.service.template.adapter.in.stream;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;
import ru.otpbank.service.template.adapter.in.stream.config.IncomeStreamBinding;
import ru.otpbank.service.template.adapter.in.stream.event.IncomingEvent;
import ru.otpbank.service.template.application.UpdateTemplateUseCase;
import ru.otpbank.service.template.application.dto.UpdateTemplateCmd;

@Component
@RequiredArgsConstructor
class TemplateEventListener {

    private final UpdateTemplateUseCase updateTemplateUseCase;

    @StreamListener(IncomeStreamBinding.INPUT)
    public void onEvent(IncomingEvent event) {
        var cmd = UpdateTemplateCmd.builder().id(event.getId()).name(event.getName()).build();
        updateTemplateUseCase.update(cmd);
    }
}
