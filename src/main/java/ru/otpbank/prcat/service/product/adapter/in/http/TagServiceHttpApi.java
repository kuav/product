package ru.otpbank.prcat.service.product.adapter.in.http;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otpbank.prcat.service.product.application.*;
import ru.otpbank.prcat.service.product.application.dto.DeleteTagCmd;
import ru.otpbank.prcat.service.product.application.dto.SearchTagQuery;
import ru.otpbank.prcat.service.product.application.dto.SearchTagsQuery;
import ru.otpbank.prcat.service.product.domain.model.Tag;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "v1/tags", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class TagServiceHttpApi {
    private final CreateTagUseCase createUseCase;
    private final UpdateTagUseCase updateUseCase;
    private final DeleteTagUseCase deleteUseCase;
    private final SearchTagUseCase searchUseCase;
    private final SearchTagsUseCase searchAllUseCase;
    private final TagHttpConverter httpConverter;

    @PutMapping("/{id}")
    public TagHttp updateTag(@PathVariable("id") String id, @RequestBody TagHttp request) {
        return httpConverter.toResponse(updateUseCase.update(httpConverter.toUpdateCmd(id, request)));
    }

    @DeleteMapping("/{id}")
    public void daleteTag(@PathVariable("id") String id) {
        deleteUseCase.delete(DeleteTagCmd.builder().id(id).build());
    }

    @PostMapping()
    public TagHttp addTag(@RequestBody TagHttp tagHttp) {
        Tag tag = createUseCase.create(httpConverter.toCreateCmd(tagHttp));
        return httpConverter.toResponse(tag);
    }

    @GetMapping("/{id}")
    public TagHttp getTag(@PathVariable("id") String id) {
        return httpConverter.toResponse(searchUseCase.search(SearchTagQuery.builder().id(id).build()));
    }

    @GetMapping()
    public List<TagHttp> getPartners() {
        return searchAllUseCase.search(SearchTagsQuery.builder().build()).stream().map(httpConverter::toResponse).collect(Collectors.toList());
    }
}
