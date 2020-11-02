package ru.otpbank.prcat.service.product.adapter.in.http;

import org.springframework.stereotype.Component;
import ru.otpbank.prcat.service.product.domain.model.CreateTagCmd;
import ru.otpbank.prcat.service.product.domain.model.Tag;
import ru.otpbank.prcat.service.product.domain.model.UpdateTagCmd;
import ru.otpbank.prcat.service.product.model.TagHttp;

@Component
public class TagHttpConverter {
    public UpdateTagCmd toUpdateCmd(String id, TagHttp request) {
        return UpdateTagCmd.builder()
                .id(id)
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public CreateTagCmd toCreateCmd(TagHttp request) {
        return CreateTagCmd.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public TagHttp toResponse(Tag tag) {
        TagHttp response = new TagHttp();
        response.setId(tag.getId());
        response.setName(tag.getName());
        response.setDescription(tag.getDescription());
        return response;
    }
}
