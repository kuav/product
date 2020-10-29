package ru.otpbank.prcat.service.product.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otpbank.prcat.service.product.application.exception.TagNotFoundException;
import ru.otpbank.prcat.service.product.domain.model.Tag;
import ru.otpbank.prcat.service.product.domain.model.UpdateTagCmd;
import ru.otpbank.prcat.service.product.domain.repository.TagRepository;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateTagUseCase {
    private final TagRepository repository;

    @Transactional
    public Tag update(UpdateTagCmd cmd) {
        return repository.save(repository.findById(cmd.getId()).orElseThrow(() -> {
            throw new TagNotFoundException(cmd.getId());
        }).update(cmd));

    }
}
