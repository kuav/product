package ru.otpbank.service.template.adapter.out.stream;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import ru.otpbank.service.template.adapter.out.stream.config.OutcomeStreamBinding;
import ru.otpbank.service.template.domain.model.TemplateEntity;

@Service
@RequiredArgsConstructor
class StreamTemplateEventPublisher {

    private final OutcomeStreamBinding outcomeStreamBinding;

    @EventListener
    public void publish(TemplateEntity entity) {
        var outcomeMsg = OutcomeEvent.builder()
                .id(entity.getId())
                .name(entity.getName())
                .type(OutcomeEvent.EventType.UPDATE)
                .build();
        var msg = MessageBuilder.withPayload(outcomeMsg).build();
        outcomeStreamBinding.output().send(msg);
    }
}
