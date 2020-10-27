package ru.otpbank.service.product.adapter.in.http;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import ru.otpbank.service.product.domain.model.CreateTagCmd;
import ru.otpbank.service.product.domain.model.Tag;
import ru.otpbank.service.product.domain.repository.TagRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class TagServiceHttpIT {
    @Autowired
    private TagRepository repository;
    @Autowired
    private TestRestTemplate restTemplate;
    private Tag tag;

    @Test
    void testCreateBadRequest() {
        TagHttp req = new TagHttp();
        ResponseEntity<String> resp = addPartnerError(req);
        assertEquals(400, resp.getStatusCodeValue());
        assertNotNull(resp.getBody());
        assertEquals("Атрибут fullName сущности Tag должен быть заполнен", resp.getBody());
    }

    @Test
    void testCreateNotUnique() {
        TagHttp req = new TagHttp();
        req.setFullName("Автострахование");
        ResponseEntity<String> resp = addPartnerError(req);
        assertEquals(400, resp.getStatusCodeValue());
        assertNotNull(resp.getBody());
        assertEquals("Tag with same fullName is already exists: Автострахование", resp.getBody());
    }

    @Test
    void testCreate() {
        TagHttp req = new TagHttp();
        req.setFullName("Страхование имущества");
        req.setShortName("Имущество");
        req.setDescription("Страхование имущества описание");
        ResponseEntity<TagHttp> resp = addTag(req);
        assertEquals(200, resp.getStatusCodeValue());
        assertNotNull(resp.getBody());
        assertNotNull(resp.getBody().getId());

        Optional<Tag> byId = repository.findById(resp.getBody().getId());
        assertTrue(byId.isPresent());
        assertEquals("Страхование имущества", byId.get().getFullName());
        assertEquals("Имущество", byId.get().getShortName());
        assertEquals("Страхование имущества описание", byId.get().getDescription());
    }

    @Test
    void testUpdate() {
        TagHttp req = new TagHttp();
        req.setFullName("Страхование авто");
        req.setShortName("Автострахование");
        req.setDescription("Страхование авто описание");

        ResponseEntity<Void> resp = updateTag(tag.getId(), req);
        assertEquals(200, resp.getStatusCodeValue());

        Optional<Tag> byId = repository.findById(tag.getId());
        assertTrue(byId.isPresent());
        assertEquals("Страхование авто", byId.get().getFullName());
        assertEquals("Автострахование", byId.get().getShortName());
        assertEquals("Страхование авто описание", byId.get().getDescription());
    }

    @Test
    void testUpdateNotFound() {
        TagHttp req = new TagHttp();
        req.setFullName("Страхование авто");
        req.setShortName("Автострахование");
        req.setDescription("Страхование авто описание");

        ResponseEntity<Void> resp = updateTag("bad id", req);
        assertEquals(404, resp.getStatusCodeValue());
    }

    @Test
    void testDeleteNotFound() {
        ResponseEntity<Void> resp = deleteTag("bad id");
        assertEquals(404, resp.getStatusCodeValue());
    }

    @Test
    void testDelete() {
        ResponseEntity<Void> resp = deleteTag(tag.getId());
        assertEquals(200, resp.getStatusCodeValue());

        Optional<Tag> byId = repository.findById(tag.getId());
        assertTrue(byId.isEmpty());
    }

    private ResponseEntity<TagHttp> addTag(TagHttp request) {
        return restTemplate.exchange("/v1/tags",
                HttpMethod.POST,
                new HttpEntity<>(request),
                TagHttp.class);
    }

    private ResponseEntity<Void> updateTag(String id, TagHttp request) {
        return restTemplate.exchange("/v1/tags/{id}", HttpMethod.PUT,
                new HttpEntity<>(
                        request),
                Void.class, id);
    }

    private ResponseEntity<Void> deleteTag(String id) {
        return restTemplate.exchange("/v1/tags/{id}", HttpMethod.DELETE,
                null,
                Void.class, id);
    }

    private ResponseEntity<String> addPartnerError(TagHttp request) {
        return restTemplate.exchange("/v1/tags",
                HttpMethod.POST,
                new HttpEntity<>(request),
                String.class);
    }

    @BeforeEach
    void setUp() {
        tag = repository.save(new Tag(CreateTagCmd.builder()
                .fullName("Автострахование")
                .shortName("Авто")
                .description("Страхование автомобилей")
                .build()));
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }
}
