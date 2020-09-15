package jpa.shop.service;

import jpa.shop.domain.Member;
import jpa.shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional(readOnly = true) //jpa 트랜잭션 안에서 데이터 변경 안되게 한다
@RequiredArgsConstructor
public class MemberService {

    //@Autowired //bean에 등록되어있는 repository를 injection
    private final MemberRepository memberRepository;

    /**      회원가입     */
    @Transactional //데이터 변경 가능하도록해준다
    public Long join(Member member) {
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    /**      중복 회원 검증     */
    private void validateDuplicateMember(Member member) {
        List<Member> findMembers =
                memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) { //멤버가 존재하면
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /**    전체 회원 조회     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**    하나의 회원 조회     */
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}