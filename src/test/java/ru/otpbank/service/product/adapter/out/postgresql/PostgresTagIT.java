package ru.otpbank.service.product.adapter.out.postgresql;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.otpbank.service.product.domain.model.CreateTagCmd;
import ru.otpbank.service.product.domain.model.Tag;
import ru.otpbank.service.product.domain.repository.TagRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles("test")
public class PostgresTagIT {
    @Autowired
    private TagRepository repository;

    @Test
    void testSaveTag() {
        Tag tag = repository.save(new Tag(CreateTagCmd.builder()
                .fullName("Автострахование")
                .shortName("Авто")
                .description("Страхование автомобилей")
                .build()));
        assertNotNull(tag);
        assertNotNull(tag.getId());

        Optional<Tag> byId = repository.findById(tag.getId());
        assertTrue(byId.isPresent());
        Tag tagById = byId.get();
        assertEquals("Автострахование", tagById.getFullName());
        assertEquals("Авто", tagById.getShortName());
        assertEquals("Страхование автомобилей", tagById.getDescription());
    }
}
