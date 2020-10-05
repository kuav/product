package ru.otpbank.service.template.adapter.in.stream.config;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.MessageChannel;

@Configuration
public interface StreamBinding {

    String INPUT = "input";

    String OUTPUT = "output";

    @Input(INPUT)
    MessageChannel input();

    @Output(OUTPUT)
    MessageChannel output();
}
