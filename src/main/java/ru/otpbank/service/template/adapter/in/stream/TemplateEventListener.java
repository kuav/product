package ru.otpbank.service.template.adapter.in.stream;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;
import ru.otpbank.service.template.adapter.in.stream.event.AdapterEvent;
import ru.otpbank.service.template.application.UpdateTemplateUseCase;

@Component
class TemplateEventListener {

    private UpdateTemplateUseCase updateTemplateUseCase;

    //@StreamListener
    public void onEvent(AdapterEvent event) {

    }
}
