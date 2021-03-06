package io.silverstring.domain.hibernate;

import io.silverstring.domain.enums.StatusEnum;
import io.silverstring.domain.enums.SupportTypeEnum;
import lombok.Data;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name="support")
public class Support {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seqGen")
    @SequenceGenerator(name = "seqGen", sequenceName = "support_seq")
    Long id;

    Long parentId;

    @OneToOne
    @JoinColumn(name="userId")
    @Lazy
    private User user;

    @Enumerated(EnumType.STRING)
    SupportTypeEnum type;

    String title;
    String content;
    LocalDateTime regDtm;
    @Enumerated(EnumType.STRING)
    StatusEnum status;
    String reason;
}
