package ru.otpbank.service.template.adapter.out.stream.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cloud.function.context.config.JsonMessageConverter;
import org.springframework.cloud.function.json.JacksonMapper;
import org.springframework.cloud.function.json.JsonMapper;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MessageConverter;

@Configuration
@EnableBinding(OutcomeStreamBinding.class)
public class OutcomeStreamConfig {

    @Bean
    public JsonMapper outcomeMessageMapper() {
        return new JacksonMapper(new ObjectMapper());
    }

    @Bean
    public MessageConverter outcomeJsonConverter() {
        return new JsonMessageConverter(outcomeMessageMapper());
    }
}
