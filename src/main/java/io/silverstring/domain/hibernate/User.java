package io.silverstring.domain.hibernate;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import io.silverstring.domain.enums.ActiveEnum;
import io.silverstring.domain.enums.LevelEnum;
import io.silverstring.domain.enums.RoleEnum;
import lombok.Data;

@Data
@Entity()
@Table(name="user", schema = "kcampus")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "userGen")
    @SequenceGenerator(name = "userGen", sequenceName = "user_seq")
    private Long id;

    private String email;
    private String pwd;

    @Enumerated(EnumType.STRING)
    private LevelEnum level;

    @Enumerated(EnumType.STRING)
    private ActiveEnum active;

    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    private String otpHash;
    
    private LocalDateTime regDtm;

    private LocalDateTime delDtm;
}
