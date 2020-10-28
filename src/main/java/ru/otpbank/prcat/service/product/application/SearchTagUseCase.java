package ru.otpbank.prcat.service.product.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otpbank.prcat.service.product.application.dto.SearchTagQuery;
import ru.otpbank.prcat.service.product.application.exception.TagNotFoundException;
import ru.otpbank.prcat.service.product.domain.model.Tag;
import ru.otpbank.prcat.service.product.domain.repository.TagRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SearchTagUseCase {
    private final TagRepository repository;

    public Tag search(SearchTagQuery query) {
        Optional<Tag> tag = repository.findById(query.getId());
        if (tag.isPresent())
            return tag.get();
        throw new TagNotFoundException(query.getId());
    }
}
