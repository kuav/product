package ru.otpbank.prcat.service.product.adapter.in.http;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otpbank.prcat.service.product.api.TagApi;
import ru.otpbank.prcat.service.product.application.*;
import ru.otpbank.prcat.service.product.application.dto.DeleteTagCmd;
import ru.otpbank.prcat.service.product.application.dto.SearchTagQuery;
import ru.otpbank.prcat.service.product.application.dto.SearchTagsQuery;
import ru.otpbank.prcat.service.product.domain.model.Tag;
import ru.otpbank.prcat.service.product.model.TagHttp;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
public class TagServiceHttpApi implements TagApi {
    private final CreateTagUseCase createUseCase;
    private final UpdateTagUseCase updateUseCase;
    private final DeleteTagUseCase deleteUseCase;
    private final SearchTagUseCase searchUseCase;
    private final SearchTagsUseCase searchAllUseCase;
    private final TagHttpConverter httpConverter;

    public ResponseEntity<TagHttp> addTag(TagHttp tagHttp) {
        Tag tag = createUseCase.create(httpConverter.toCreateCmd(tagHttp));
        return ResponseEntity.ok(httpConverter.toResponse(tag));
    }

    public ResponseEntity<TagHttp> updateTag(String id, TagHttp request) {
        return ResponseEntity.ok(httpConverter.toResponse(updateUseCase.update(httpConverter.toUpdateCmd(id, request))));
    }

    public ResponseEntity<Void> deleteTag(String id) {
        deleteUseCase.delete(DeleteTagCmd.builder().id(id).build());
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<TagHttp> getTagById(String id) {
        return ResponseEntity.ok(httpConverter.toResponse(searchUseCase.search(SearchTagQuery.builder().id(id).build())));
    }

    public ResponseEntity<List<TagHttp>> findTags() {
        return ResponseEntity.ok(searchAllUseCase.search(SearchTagsQuery.builder().build()).stream().map(httpConverter::toResponse).collect(Collectors.toList()));
    }
}
