package ru.otpbank.prcat.service.product.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otpbank.prcat.service.product.domain.model.Tag;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag, String> {
    List<Tag> findByFullName(String fullName);
}
