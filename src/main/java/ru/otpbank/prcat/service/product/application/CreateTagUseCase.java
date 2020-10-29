package ru.otpbank.prcat.service.product.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otpbank.prcat.service.product.application.exception.TagUniqueViolationException;
import ru.otpbank.prcat.service.product.domain.model.CreateTagCmd;
import ru.otpbank.prcat.service.product.domain.model.Tag;
import ru.otpbank.prcat.service.product.domain.repository.TagRepository;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CreateTagUseCase {
    private final TagRepository repository;

    @Transactional
    public Tag create(CreateTagCmd cmd) {
        if (repository.existsByFullName(cmd.getFullName()))
            throw new TagUniqueViolationException(cmd.getFullName());
        return repository.save(new Tag(cmd));
    }
}
