package ru.otpbank.prcat.service.product.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otpbank.prcat.service.product.application.dto.SearchTagsQuery;
import ru.otpbank.prcat.service.product.domain.model.Tag;
import ru.otpbank.prcat.service.product.domain.repository.TagRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchTagsUseCase {
    private final TagRepository repository;

    public List<Tag> search(SearchTagsQuery query) {
        return repository.findAll();
    }
}
