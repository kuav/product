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

    @Column(name = "FULL_NAME")
    private String fullName;

    @Column(name = "SHORT_NAME")
    private String shortName;

    @Column(name = "DESCRIPTION")
    private String description;

    public Tag(CreateTagCmd cmd) {
        this(generateUUID(),
                cmd.getFullName(),
                cmd.getShortName(),
                cmd.getDescription());
    }

    public Tag update(UpdateTagCmd cmd) {
        this.fullName = cmd.getFullName();
        this.shortName = cmd.getShortName();
        this.description = cmd.getDescription();
        return this;
    }
}
