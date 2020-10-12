package ru.otpbank.service.template.adapter.out.postgresql;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.otpbank.service.template.domain.model.TemplateEntity;
import ru.otpbank.service.template.domain.repository.TemplateEntityRepository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public
class PostgreSqlTemplateEntityRepository implements TemplateEntityRepository {

    private static final String FIND_BY_ID_SQL = "SELECT id, name FROM template";

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Optional<TemplateEntity> findById(String id) {
        return jdbcTemplate.queryForObject(FIND_BY_ID_SQL,
                (rs, rn) -> Optional.of(
                        new TemplateEntity(rs.getString("id"), rs.getString("name"))
                ));
    }
}
