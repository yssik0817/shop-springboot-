package jpa.shop.repository;

import jpa.shop.domain.Member;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MemberRepository {

    @PersistenceContext //엔티티메니저 주입
    private EntityManager em;

    //@PersistenceUnit
    //private EntityManagerFactory emf;

    public void save(Member member) {
        em.persist(member);
    }
    //단건 조회
    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }
    //전체 조회
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class) //jpql,반환타입
                .getResultList();
    }
    //이름으로 검색
    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
