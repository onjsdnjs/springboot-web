package io.silverstring.domain.hibernate;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Data
@Entity
@Table(name="ico_recommend", schema = "springboot")
public class IcoRecommend {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seqGen")
    @SequenceGenerator(name = "seqGen", sequenceName = "ico_recommend_seq")
    private Long id;

    private String title;
    private String url;
    private String content;
    private String email;
    private String imgUrl;
}
