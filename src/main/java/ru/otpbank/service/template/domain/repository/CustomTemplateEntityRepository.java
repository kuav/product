package ru.otpbank.service.template.domain.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.otpbank.service.template.domain.model.TemplateEntity;

import java.util.List;

@Repository
public interface CustomTemplateEntityRepository {

    @Query("SELECT id, name FROM template")
    List<TemplateEntity> customTemplateFindAll();

    @Query(value = "SELECT id, name FROM template where id = ? AND name = ?", nativeQuery = true)
    TemplateEntity customTemplateFindEntityNative(Long id, String name);

    @Query(value = "SELECT t.id, t.name FROM TemplateEntity t where t.id = :id AND t.name = :name")
    TemplateEntity customTemplateFindEntity(@Param("id") Long id, @Param("name") String name);

    @Modifying
    @Query(value = "UPDATE template set name = ? where id = ?", nativeQuery = true)
    int setFirstnameFor(String name, Long id);
}
