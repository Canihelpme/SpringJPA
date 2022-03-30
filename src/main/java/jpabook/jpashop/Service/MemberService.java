package jpabook.jpashop.Service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) //모든 수정에는 transaction 있어야 함.
@RequiredArgsConstructor
public class MemberService {

    @Autowired
    private final MemberRepository memberRepository;

    /**
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    } by @RequiredArgsConstructor
     **/

    //회원 가입
    @Transactional
    public Long join(Member member) {
        valiedateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void valiedateDuplicateMember(Member member) {
        //동시 입력시 문제 발생 가능하므로 multi thread 사용 혹은 member의 name을 unique 제약조건 걸자.
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
        //Exception 처리
    }


    //회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    //회원 단건 조회
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

    @Transactional
    public void update(Long id, String name) {
        Member member = memberRepository.findOne(id);
        member.setName(name);
    }
}
