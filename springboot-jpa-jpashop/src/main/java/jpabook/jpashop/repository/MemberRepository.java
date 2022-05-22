package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

//    @PersistenceContext
//    private EntityManager em;
    private final EntityManager em;

    public void save(Member member) {
        em.persist(member); // 영속성 컨텍스트에 저장 -> commit 이후 디비 저장
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id); // PK 사용
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class) // JPQL
                .getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name= :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
