package ru.otpbank.service.product.adapter.out.postgresql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otpbank.service.product.domain.model.Tag;

@Repository
public interface TagRepositoryForTest extends JpaRepository<Tag, String> {

}