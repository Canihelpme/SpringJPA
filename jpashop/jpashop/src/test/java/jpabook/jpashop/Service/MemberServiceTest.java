package jpabook.jpashop.Service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class) //Spring과 같이 실행 위해
@SpringBootTest //Springboot띄운채로 Test for @Autowired.
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em; //DB에 강제로 영속성 통해 Insert

    @Test
    public void 회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setName("Shin");

        //when
        Long savedId = memberService.join(member);
        //em.flush(); //DB에 강제로 영속성 통해 Insert
        //then
        assertEquals(member, memberRepository.findOne(savedId));
        //같은 Transaction 안에서 존재하기에 가능.
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("shin");

        Member member2 = new Member();
        member2.setName("shin");

        //when
        memberService.join(member1);
        memberService.join(member2);

        /**
        try {
            memberService.join(member2);
        } catch (IllegalStateException e) {
            return;
        }
        **/

        //then
        fail("예외 발생.");

    }

}