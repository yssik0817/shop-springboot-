package jpa.shop.domain.item;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("M") //싱글 테이블에 구분하기 위해 넣는 값
@Getter @Setter
public class Movie extends Item {
    private String director;
    private String actor;
}