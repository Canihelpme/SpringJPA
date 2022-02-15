package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository //Component Scan에 의해 bean에 자동등록 됨.
@RequiredArgsConstructor
public class MemberRepository {

    //@PersistenceContext Entity manager injection 위해. 표준 Injection
    @Autowired //Spring data jpa도 injection 지원.
    private final EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }
    //단건 조회
    public Member findOne(Long id) {
        return em.find(Member.class, id);
        //==Member member = em.find(Member.class, id);
    }

    //여러개 조회
   public List<Member> findAll() {
        //query문(Entity를 대상으로) 과 return type 정의 후 return
        //jpa는 객체에 대한 m
        return em.createQuery("select m from Member m ", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name) {
                                                        //Parameter binding
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }

}
