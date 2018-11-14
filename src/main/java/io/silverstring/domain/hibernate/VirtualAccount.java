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
@Table(name="virtual_account")
public class VirtualAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seqGen")
    @SequenceGenerator(name = "seqGen", sequenceName = "virtual_account_seq")
    private Long id;

    private Long userId;
    private String account;
    private String bankName;
    private String bankCode;
    private String recvCorpNm;
    private LocalDateTime regDtm;
    private LocalDateTime allocDtm;
}
