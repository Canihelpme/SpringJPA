package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id") //Set the column of PK
    private Long id; //em.persis() 실행시 pk 값으로 저장

    //@NotEmpty
    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member") //Order 의 member field 의존, 연관관계 주종 설정.
    private List<Order> orders = new ArrayList<>();
}
