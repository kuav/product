package ru.otpbank.service.template.adapter.in.http;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.otpbank.service.template.application.CreateTemplateUseCase;

@RestController
@RequestMapping(path = "/templates", produces = APPLICATION_JSON_VALUE)
class TemplateServiceHttpApi {

    private CreateTemplateUseCase createTemplateUseCase;
    
    @GetMapping("/")
    public String hello() {
        return "Hello Java template";
    }
}
