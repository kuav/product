package ru.otpbank.prcat.service.product.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otpbank.prcat.service.product.application.dto.SearchTagQuery;
import ru.otpbank.prcat.service.product.application.exception.TagNotFoundException;
import ru.otpbank.prcat.service.product.domain.model.Tag;
import ru.otpbank.prcat.service.product.domain.repository.TagRepository;

@Service
@RequiredArgsConstructor
public class SearchTagUseCase {
    private final TagRepository repository;

    public Tag search(SearchTagQuery query) {
        return repository.findById(query.getId()).orElseThrow(() -> {
            throw new TagNotFoundException(query.getId());
        });

    }
}
