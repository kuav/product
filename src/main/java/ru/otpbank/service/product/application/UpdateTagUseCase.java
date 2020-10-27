package ru.otpbank.service.product.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otpbank.service.product.application.exception.TagNotFoundException;
import ru.otpbank.service.product.domain.model.UpdateTagCmd;
import ru.otpbank.service.product.domain.repository.TagRepository;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateTagUseCase {
    private final TagRepository repository;

    @Transactional
    public void update(UpdateTagCmd cmd) {
        repository.findById(cmd.getId()).ifPresentOrElse(tag -> repository.save(tag.update(cmd)),
                () -> {
                    throw new TagNotFoundException(cmd.getId());
                });
    }
}
