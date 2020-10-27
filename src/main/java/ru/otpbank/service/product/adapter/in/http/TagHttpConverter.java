package ru.otpbank.service.product.adapter.in.http;

import org.springframework.stereotype.Component;
import ru.otpbank.service.product.domain.model.CreateTagCmd;
import ru.otpbank.service.product.domain.model.Tag;
import ru.otpbank.service.product.domain.model.UpdateTagCmd;

@Component
public class TagHttpConverter {
    public UpdateTagCmd toUpdateCmd(String id, TagHttp request) {
        return UpdateTagCmd.builder()
                .id(id)
                .fullName(request.getFullName())
                .shortName(request.getShortName())
                .description(request.getDescription())
                .build();
    }

    public CreateTagCmd toCreateCmd(TagHttp request) {
        return CreateTagCmd.builder()
                .fullName(request.getFullName())
                .shortName(request.getShortName())
                .description(request.getDescription())
                .build();
    }

    public TagHttp toResponse(Tag tag) {
        TagHttp response = new TagHttp();
        response.setId(tag.getId());
        response.setFullName(tag.getFullName());
        response.setShortName(tag.getShortName());
        response.setDescription(tag.getDescription());
        return response;
    }
}
