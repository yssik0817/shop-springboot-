package jpa.shop.domain;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Embeddable;

@Embeddable //내장형
@Getter
//getter는 열고 setter는 꼭 필요한 경우만, 생성자에서 값을 모두 초기화해서 변경 불가능한 클래스 만들기
public class Address {
    private String city;
    private String street;
    private String zipcode;
    protected Address() {
    }
    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}