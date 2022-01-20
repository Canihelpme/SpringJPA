package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name= "member_id") //FK 이름 설정. Mapping을 이걸로 함.
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<orderItem> orderItems = new ArrayList<>(); //Hybernate 오류 방지 위해.

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status; //주문상태 Order, Cancel

    //==연관관계 메서도(양방향 세팅)==//
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this); //member에 order추가
    }

    public void addOrderItem(orderItem orderItem){
        this.orderItems.add(orderItem);
        orderItem.setOrder(this); //order에 member추가
    }

    public void setDelivery(Delivery delivery){
        this.delivery = delivery;
        delivery.setOrder(this);
    }




}
