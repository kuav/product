package ru.otpbank.service.product.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otpbank.service.product.application.exception.TagUniqueViolationException;
import ru.otpbank.service.product.domain.model.CreateTagCmd;
import ru.otpbank.service.product.domain.model.Tag;
import ru.otpbank.service.product.domain.repository.TagRepository;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CreateTagUseCase {
    private final TagRepository repository;

    @Transactional
    public Tag create(CreateTagCmd cmd) {
        if (repository.findByFullName(cmd.getFullName()).stream().findFirst().isPresent())
            throw new TagUniqueViolationException(cmd.getFullName());
        return repository.save(new Tag(cmd));
    }
}