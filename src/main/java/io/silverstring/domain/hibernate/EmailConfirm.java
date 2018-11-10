package io.silverstring.domain.hibernate;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import io.silverstring.domain.enums.ActiveEnum;
import lombok.Data;

@Data
@Entity
@IdClass(EmailConfirmPK.class)
@Table(name="email.confirm", schema = "kcampus")
public class EmailConfirm {

    @Id
    private String hashEmail;

    @Id
    private String code;
    private String email;

    @Enumerated(EnumType.STRING)
    private ActiveEnum sendYn;

    private LocalDateTime regDtm;
}
