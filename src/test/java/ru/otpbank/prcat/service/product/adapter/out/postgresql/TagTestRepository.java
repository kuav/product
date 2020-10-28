package ru.otpbank.prcat.service.product.adapter.out.postgresql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otpbank.prcat.service.product.domain.model.Tag;

@Repository
public interface TagTestRepository extends JpaRepository<Tag, String> {

}