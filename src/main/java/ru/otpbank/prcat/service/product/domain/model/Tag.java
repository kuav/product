package ru.otpbank.prcat.service.product.domain.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import static ru.otpbank.prcat.service.product.common.CommonUtils.generateUUID;

@Entity
@Table(name = "TAG")
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Tag {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    public Tag(CreateTagCmd cmd) {
        this(generateUUID(),
                cmd.getName(),
                cmd.getDescription());
    }

    public Tag update(UpdateTagCmd cmd) {
        this.name = cmd.getName();
        this.description = cmd.getDescription();
        return this;
    }
}
