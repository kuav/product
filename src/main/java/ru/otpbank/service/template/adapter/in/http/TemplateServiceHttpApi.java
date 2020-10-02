package ru.otpbank.service.template.adapter.in.http;

import org.springframework.web.bind.annotation.RestController;
import ru.otpbank.service.template.application.CreateTemplateUseCase;

@RestController
class TemplateServiceHttpApi {

    private CreateTemplateUseCase createTemplateUseCase;
}
