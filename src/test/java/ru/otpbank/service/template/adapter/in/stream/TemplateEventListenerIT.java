package ru.otpbank.service.template.adapter.in.stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.messaging.support.MessageBuilder;
import ru.otpbank.service.template.adapter.in.stream.config.IncomeStreamBinding;
import ru.otpbank.service.template.adapter.in.stream.event.IncomingEvent;
import ru.otpbank.service.template.adapter.out.stream.config.OutcomeStreamBinding;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class TemplateEventListenerIT {

    @Autowired
    private MessageCollector messageCollector;

    @Autowired
    private IncomeStreamBinding incomeStreamBinding;

    @Autowired
    private OutcomeStreamBinding outcomeStreamBinding;

    @Test
    @DisplayName("Проверка обработки сообщения")
    void shouldProcessAndSendMessage() {
        var incomingMessage = IncomingEvent.builder().id("someId").name("someName").build();
        var msg = MessageBuilder.withPayload(incomingMessage).build();
        incomeStreamBinding.input().send(msg);
        var message = messageCollector.forChannel(outcomeStreamBinding.output()).poll();
        assertThat(message).isNotNull();
        var out = (String) message.getPayload();
        assertThat(out).isEqualTo("{\"id\":\"someId\",\"name\":\"someName\",\"type\":\"UPDATE\"}");
    }
}
