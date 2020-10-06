package ru.otpbank.service.template.adapter.in.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import ru.otpbank.service.template.adapter.in.stream.config.IncomeStreamConfig;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class TemplateEventListenerIT {

    @Autowired
    private MessageCollector messageCollector;

    @Autowired
    private IncomeStreamConfig incomeStreamConfig;
}
