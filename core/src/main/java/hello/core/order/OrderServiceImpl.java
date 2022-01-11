package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor 생성자 자동 주입시 사용.
public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;
    ////final 있으면 기본으로 생성자 할당 필요.
    //RequiredArgsConstructor 사용시 생성자 자동으로 생성해줌.

    //@Autowired
    //private DiscountPolicy rateDiscountPolicy;
    //xml도 Rate로 변경 필요

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        //Order 만들어서 반환.
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //Singleton 검증 위한 간단히만 구현 Interface에는 구현 안함.
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
