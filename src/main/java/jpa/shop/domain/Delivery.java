package jpa.shop.domain;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Getter @Setter
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING) //EnumType 기본값은 ordinal(숫자), 꼭 String
    private DeliveryStatus status; //ENUM [READY(준비), COMP(배송)]
}
