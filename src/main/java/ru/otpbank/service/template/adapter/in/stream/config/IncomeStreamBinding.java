package ru.otpbank.service.template.adapter.in.stream.config;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.MessageChannel;

@Configuration
public interface IncomeStreamBinding {

    String INPUT = "input";

    @Input(INPUT)
    MessageChannel input();
}
