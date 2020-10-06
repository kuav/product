package ru.otpbank.service.template.adapter.out.stream;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OutcomeEvent {

    private final String id;

    private final String name;

    private final EventType type;

    enum EventType {

        UPDATE
    }
}
