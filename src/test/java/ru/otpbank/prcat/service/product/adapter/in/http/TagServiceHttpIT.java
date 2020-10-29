package ru.otpbank.prcat.service.product.adapter.in.http;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import ru.otpbank.prcat.service.product.adapter.out.postgresql.TagTestRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static ru.otpbank.prcat.service.product.adapter.in.http.TagMother.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class TagServiceHttpIT {
    @Autowired
    private TagTestRepository repository;
    @Autowired
    private TestRestTemplate restTemplate;
    private TagHttp tag;

    @BeforeEach
    void setUp() {
        tag = addTag(avto().build()).getBody();
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void testCreateBadRequest() {
        ResponseEntity<String> resp = addPartnerError(empty().build());
        assertEquals(400, resp.getStatusCodeValue());
        assertNotNull(resp.getBody());
        assertEquals("Атрибут fullName сущности Tag должен быть заполнен", resp.getBody());
    }

    @Test
    void testCreateNotUnique() {
        ResponseEntity<String> resp = addPartnerError(avto().build());
        assertEquals(400, resp.getStatusCodeValue());
        assertNotNull(resp.getBody());
        assertEquals("Tag with same fullName is already exists", resp.getBody());
    }

    @Test
    void testCreate() {
        ResponseEntity<TagHttp> resp = addTag(estate().build());
        assertEquals(200, resp.getStatusCodeValue());
        assertNotNull(resp.getBody());
        assertNotNull(resp.getBody().getId());

        ResponseEntity<TagHttp> byId = getTag(resp.getBody().getId());
        assertEquals(200, byId.getStatusCodeValue());
        assertNotNull(byId.getBody());
        assertEquals(ESTATE_FULLNAME, byId.getBody().getFullName());
        assertEquals(ESTATE_SHORTNAME, byId.getBody().getShortName());
        assertEquals(ESTATE_DESCRIPTION, byId.getBody().getDescription());
    }

    @Test
    void testUpdate() {
        ResponseEntity<TagHttp> resp = updateTag(tag.getId(), avto()
                .fullName("Страхование авто")
                .shortName("Автострахование")
                .description("Страхование авто описание")
                .build());
        assertEquals(200, resp.getStatusCodeValue());

        ResponseEntity<TagHttp> byId = getTag(tag.getId());
        assertEquals(200, byId.getStatusCodeValue());
        assertNotNull(byId.getBody());
        assertEquals("Страхование авто", byId.getBody().getFullName());
        assertEquals("Автострахование", byId.getBody().getShortName());
        assertEquals("Страхование авто описание", byId.getBody().getDescription());
    }

    @Test
    void testUpdateNotFound() {
        ResponseEntity<String> resp = updateTagError("bad id", estate().build());
        assertEquals(404, resp.getStatusCodeValue());
    }

    @Test
    void testUpdateUniqueException() {
        ResponseEntity<TagHttp> resp = addTag(estate().build());
        assertEquals(200, resp.getStatusCodeValue());

        ResponseEntity<String> respUpdate = updateTagError(resp.getBody().getId(), avto()
                .build());
        assertEquals(400, respUpdate.getStatusCodeValue());
        assertNotNull(respUpdate.getBody());
        assertEquals("Tag with same fullName is already exists", respUpdate.getBody());
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

        ResponseEntity<Void> byId = getTagNotFound(tag.getId());
        assertEquals(404, byId.getStatusCodeValue());
    }

    @Test
    void testGetAll() {
        ResponseEntity<List<TagHttp>> resp = getAllTag();
        assertEquals(200, resp.getStatusCodeValue());
        assertEquals(1, resp.getBody().size());
        TagHttp tagHttp = resp.getBody().get(0);
        assertEquals(tag.getId(), tagHttp.getId());
        assertEquals(tag.getFullName(), tagHttp.getFullName());
        assertEquals(tag.getShortName(), tagHttp.getShortName());
        assertEquals(tag.getDescription(), tagHttp.getDescription());
    }

    private ResponseEntity<TagHttp> addTag(TagHttp request) {
        return restTemplate.exchange("/v1/tags",
                HttpMethod.POST,
                new HttpEntity<>(request),
                TagHttp.class);
    }

    private ResponseEntity<TagHttp> updateTag(String id, TagHttp request) {
        return restTemplate.exchange("/v1/tags/{id}", HttpMethod.PUT,
                new HttpEntity<>(
                        request),
                TagHttp.class, id);
    }

    private ResponseEntity<String> updateTagError(String id, TagHttp request) {
        return restTemplate.exchange("/v1/tags/{id}", HttpMethod.PUT,
                new HttpEntity<>(
                        request),
                String.class, id);
    }

    private ResponseEntity<Void> deleteTag(String id) {
        return restTemplate.exchange("/v1/tags/{id}", HttpMethod.DELETE,
                null,
                Void.class, id);
    }

    private ResponseEntity<TagHttp> getTag(String id) {
        return restTemplate.exchange("/v1/tags/{id}", HttpMethod.GET,
                null,
                TagHttp.class, id);
    }

    private ResponseEntity<Void> getTagNotFound(String id) {
        return restTemplate.exchange("/v1/tags/{id}", HttpMethod.GET,
                null,
                Void.class, id);
    }

    private ResponseEntity<List<TagHttp>> getAllTag() {
        return restTemplate.exchange("/v1/tags", HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                });
    }

    private ResponseEntity<String> addPartnerError(TagHttp request) {
        return restTemplate.exchange("/v1/tags",
                HttpMethod.POST,
                new HttpEntity<>(request),
                String.class);
    }

}
