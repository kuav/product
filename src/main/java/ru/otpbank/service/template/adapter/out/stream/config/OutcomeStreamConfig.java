package ru.otpbank.service.template.adapter.out.stream.config;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBinding(OutcomeStreamBinding.class)
public class OutcomeStreamConfig {
}
