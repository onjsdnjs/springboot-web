package io.silverstring.domain.hibernate;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@IdClass(MyCouponPK.class)
@Table(name="my_coupon", schema = "springboot")
public class MyCoupon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seqGen")
    @SequenceGenerator(name = "seqGen", sequenceName = "my_coupon_seq")
    Long id;

    @Id
    Long userId;

    @OneToOne
    @JoinColumn(name="couponId")
    private Coupon coupon;

    LocalDateTime regDtm;
    LocalDateTime usedBeginDtm;
    LocalDateTime expireDtm;
}
