package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
//@Qualifier("mainDiscountPolicy") 이러면 typing error 발생되니까
@MainDiscountPolicy //이렇게 연결해주자.
public class RateDiscountPolicy implements DiscountPolicy{

    private int discountpercent = 10;

    @Override
    public int discount(Member member, int price) {
         if (member.getGrade() == Grade.VIP){
             return price * discountpercent / 100;
         }
         else {
             return 0;
         }
    }
}
