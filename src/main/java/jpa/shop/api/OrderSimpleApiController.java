package jpa.shop.api;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import jpa.shop.domain.Address;
import jpa.shop.domain.Order;
import jpa.shop.domain.OrderStatus;
import jpa.shop.repository.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.util.List;
import static java.util.stream.Collectors.toList;
/**
 *
 * xToOne(ManyToOne, OneToOne) 관계 최적화
 * Order
 * Order -> Member
 * Order -> Delivery
 *
 */
@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {
    private final OrderRepository orderRepository;

    @Bean
    Hibernate5Module hibernate5Module() {
        return new Hibernate5Module();
    }

    /**
     * V1. 엔티티 직접 노출
     * - Hibernate5Module 모듈 등록, LAZY=null 처리
     * - 양방향 관계 문제 발생 -> @JsonIgnore
     */
    @GetMapping("/api/v1/simple-orders")
    public List<Order> ordersV1() {
        List<Order> all = orderRepository.findAll();
        for (Order order : all) {
            order.getMember().getName(); //Lazy 강제 초기화
            order.getDelivery().getAddress(); //Lazy 강제 초기환
        }
        return all;
    }
}