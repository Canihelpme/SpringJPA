package jpabook.jpashop.repository;

import jpabook.jpashop.domain.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderSearch {

    private String memberName; //회원이름
    private OrderStatus orderStatus; //주문상태 order, cancel
    //Parmeter 조건이 있으면 JPQL where문으로 검색 가능.
}
