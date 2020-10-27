package ru.otpbank.service.product.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otpbank.service.product.application.exception.TagNotFoundException;
import ru.otpbank.service.product.domain.model.Tag;
import ru.otpbank.service.product.domain.model.UpdateTagCmd;
import ru.otpbank.service.product.domain.repository.TagRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpdateTagUseCase {
    private final TagRepository repository;

    @Transactional
    public Tag update(UpdateTagCmd cmd) {
        Optional<Tag> byId = repository.findById(cmd.getId());
        if (byId.isPresent())
            return repository.save(byId.get().update(cmd));

        throw new TagNotFoundException(cmd.getId());
    }
}
