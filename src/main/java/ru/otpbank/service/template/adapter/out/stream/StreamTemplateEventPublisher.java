package ru.otpbank.service.template.adapter.out.stream;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import ru.otpbank.service.template.adapter.out.stream.config.OutcomeStreamBinding;
import ru.otpbank.service.template.domain.model.event.TemplateChanged;

@Service
@RequiredArgsConstructor
class StreamTemplateEventPublisher {

    private final OutcomeStreamBinding outcomeStreamBinding;

    @EventListener
    public void publish(TemplateChanged event) {
        var outcomeMsg = OutcomeEvent.builder()
                .id(event.getId())
                .name(event.getName())
                .type(OutcomeEvent.EventType.UPDATE)
                .build();
        var msg = MessageBuilder.withPayload(outcomeMsg).build();
        outcomeStreamBinding.output().send(msg);
    }
}
