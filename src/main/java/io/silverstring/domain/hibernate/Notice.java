package io.silverstring.domain.hibernate;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name="notice", schema = "kcampus")
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seqGen")
    @SequenceGenerator(name = "seqGen", sequenceName = "notice_seq")
    private Long id;

    private String title;
    private String content;
    private String url;

    private LocalDateTime regDtm;
    private LocalDateTime delDtm;
}
