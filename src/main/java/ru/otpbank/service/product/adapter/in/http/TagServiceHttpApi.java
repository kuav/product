package ru.otpbank.service.product.adapter.in.http;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otpbank.service.product.application.CreateTagUseCase;
import ru.otpbank.service.product.application.DeleteTagUseCase;
import ru.otpbank.service.product.application.UpdateTagUseCase;
import ru.otpbank.service.product.application.dto.DeleteTagCmd;
import ru.otpbank.service.product.application.exception.TagNotFoundException;
import ru.otpbank.service.product.application.exception.TagUniqueViolationException;
import ru.otpbank.service.product.domain.model.Tag;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "v1/tags", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class TagServiceHttpApi {
    private final CreateTagUseCase createUseCase;
    private final UpdateTagUseCase updateUseCase;
    private final DeleteTagUseCase deleteUseCase;
    private final TagHttpConverter httpConverter;

    @PutMapping("/{id}")
    public void updateTag(@PathVariable("id") String id, @RequestBody TagHttp request) {
        updateUseCase.update(httpConverter.toUpdateCmd(id, request));
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

    @ExceptionHandler(TagUniqueViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<String> handleTagNotFoundException(TagUniqueViolationException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TagNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ResponseEntity<String> handleTagNotFoundException(TagNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
