package ru.otpbank.service.template.adapter.in.stream.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cloud.function.context.config.JsonMessageConverter;
import org.springframework.cloud.function.json.JacksonMapper;
import org.springframework.cloud.function.json.JsonMapper;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MessageConverter;

@Configuration
@EnableBinding(IncomeStreamBinding.class)
public class IncomeStreamConfig {

    @Bean
    public JsonMapper incomeMessageMapper() {
        return new JacksonMapper(new ObjectMapper());
    }

    @Bean
    public MessageConverter incomeJsonConverter() {
        return new JsonMessageConverter(incomeMessageMapper());
    }
}
