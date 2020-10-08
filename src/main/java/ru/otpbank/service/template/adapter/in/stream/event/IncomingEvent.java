package ru.otpbank.service.template.adapter.in.stream.event;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class IncomingEvent {

    private final String id;

    private final String name;

    private final EventType type;

    enum EventType {

        UPDATE
    }
}
