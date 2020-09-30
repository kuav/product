package ru.otpbank.template.adapter.in.stream.event;

public class AdapterEvent {

    private String id;

    private EventType type;

    enum EventType {

        UPDATE
    }
}
