package ru.otpbank.service.product.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otpbank.service.product.application.dto.DeleteTagCmd;
import ru.otpbank.service.product.application.exception.TagNotFoundException;
import ru.otpbank.service.product.domain.repository.TagRepository;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteTagUseCase {
    private final TagRepository repository;

    @Transactional
    public void delete(DeleteTagCmd cmd) {
        repository.findById(cmd.getId()).ifPresentOrElse(tag -> repository.deleteById(cmd.getId()),
                () -> {
                    throw new TagNotFoundException(cmd.getId());
                });
    }
}
