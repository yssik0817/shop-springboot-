package jpa.shop.api;

import jpa.shop.domain.Member;
import jpa.shop.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberApiController {
    private final MemberService memberService;
/**
 * 조회 V1: 응답 값으로 엔티티를 직접 외부에 노출한다.
 * 문제점
 * - 엔티티에 프레젠테이션 계층을 위한 로직이 추가된다.
 * - 기본적으로 엔티티의 모든 값이 노출된다.
 * - 응답 스펙을 맞추기 위해 로직이 추가된다. (@JsonIgnore, 별도의 뷰 로직 등등)
 * - 실무에서는 같은 엔티티에 대해 API가 용도에 따라 다양하게 만들어지는데, 한 엔티티에 각각의
 API를 위한 프레젠테이션 응답 로직을 담기는 어렵다.
 * - 엔티티가 변경되면 API 스펙이 변한다.
 * - 추가로 컬렉션을 직접 반환하면 항후 API 스펙을 변경하기 어렵다.(별도의 Result 클래스 생성
 으로 해결)
 * 결론
 * - API 응답 스펙에 맞추어 별도의 DTO를 반환한다.
 */
//조회 V1: 안 좋은 버전, 모든 엔티티가 노출, @JsonIgnore -> 이건 정말 최악, api가 이거 하나인가! 화면에 종속적이지 마라!
    @GetMapping("/api/v1/members")
    public List<Member> membersV1() {
        return memberService.findMembers();
    }
    /**
     * 조회 V2: 응답 값으로 엔티티가 아닌 별도의 DTO를 반환한다.
     */
    @GetMapping("/api/v2/members")
    public Result membersV2() {
        List<Member> findMembers = memberService.findMembers();
//엔티티 -> DTO 변환
        List<MemberDto> collect = findMembers.stream()
                .map(m -> new MemberDto(m.getName()))
                .collect(Collectors.toList());
        return new Result(collect);
    }
    @Data
    @AllArgsConstructor
    class Result<T> {
        private T data;
    }
    @Data
    @AllArgsConstructor
    class MemberDto {
        private String name;
    }
}