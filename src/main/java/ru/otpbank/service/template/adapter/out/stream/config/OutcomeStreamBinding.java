package ru.otpbank.service.template.adapter.out.stream.config;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.MessageChannel;

@Configuration
public interface OutcomeStreamBinding {

    String OUTPUT = "output";

    @Output(OUTPUT)
    MessageChannel output();
}
