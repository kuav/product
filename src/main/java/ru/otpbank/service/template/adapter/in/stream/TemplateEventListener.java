package ru.otpbank.service.template.adapter.in.stream;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;
import ru.otpbank.service.template.adapter.in.stream.config.StreamBinding;
import ru.otpbank.service.template.adapter.in.stream.event.AdapterEvent;
import ru.otpbank.service.template.application.UpdateTemplateUseCase;
import ru.otpbank.service.template.application.dto.UpdateTemplateCmd;

@Component
@RequiredArgsConstructor
class TemplateEventListener {

    private final UpdateTemplateUseCase updateTemplateUseCase;

    @StreamListener(StreamBinding.INPUT)
    public void onEvent(AdapterEvent event) {
        var cmd = new UpdateTemplateCmd();
        updateTemplateUseCase.update(cmd);
    }
}
