package ru.otpbank.service.template;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.test.context.ActiveProfiles;
import ru.otpbank.service.template.domain.model.TemplateEntity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootTest
@ActiveProfiles("test")
public class PostgresTemplateIT {

    @Autowired
    private NamedParameterJdbcOperations jdbcTemplate;

    @Test
    @DisplayName("Проверка накатки миграций и суммарных данных")
    public void find_templates_test() {

        final var allTemplates = jdbcTemplate.query(
                "SELECT id, name FROM TEMPLATE",
                (rs, rn) -> new TemplateEntity(rs.getString("id"), rs.getString("name")));
        Assertions.assertEquals(2, allTemplates.size());
        final List<String> expectedIds = Arrays.asList("7a7ef5a6-d1cd-406c-8aa8-91c8e4e3b710", "7a7ef5a6-d1cd-406c-8aa8-91c8e4e3b711");
        Assertions.assertTrue(allTemplates.stream()
                .map(TemplateEntity::getId)
                .collect(Collectors.toList())
                .containsAll(expectedIds));
    }

    @Test
    @DisplayName("Проверка наличия записи из миграции")
    public void prepared_statement_query_test() {
        final var id = "7a7ef5a6-d1cd-406c-8aa8-91c8e4e3b711";

        Map<String, Object> params = new HashMap<>();
        params.put("id", id);

        final var names = jdbcTemplate.query(
                "SELECT name FROM TEMPLATE WHERE id = :id", params,
                (resultSet, rowNum) -> resultSet.getString("name"));
        Assertions.assertEquals(1, names.size());
        Assertions.assertEquals("Test template name", names.get(0));
    }
}
