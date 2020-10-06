package ru.otpbank.service.template.adapter.out.stream;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otpbank.service.template.adapter.in.stream.config.IncomeStreamBinding;
import ru.otpbank.service.template.domain.model.TemplateEntity;

@Service
@RequiredArgsConstructor
class StreamTemplateEventPublisher {

    private final IncomeStreamBinding incomeStreamBinding;

    public void publish(TemplateEntity entity) {

    }
}
