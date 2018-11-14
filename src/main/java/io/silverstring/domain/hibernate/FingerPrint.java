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
@IdClass(FingerPrintPK.class)
@Table(name="finger_print")
public class FingerPrint {

    @Id
    private Long userId;

    @Id
    private String hashKey;

    private LocalDateTime regDtm;
    
    private LocalDateTime delDtm;

    @Enumerated(EnumType.STRING)
    private ActiveEnum active;
}
