package ru.otpbank.service.template.adapter.in.stream.config;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBinding(IncomeStreamBinding.class)
public class IncomeStreamConfig {
}
