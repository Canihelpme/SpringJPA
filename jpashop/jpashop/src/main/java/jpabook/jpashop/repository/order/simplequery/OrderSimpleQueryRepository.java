package jpabook.jpashop.repository.order.simplequery;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderSimpleQueryRepository {

    private final EntityManager em;

    public List<OrderSimpleQueryDto> findOrdersDtos() {
        return em.createQuery("select new jpabook.jpashop.repository.order.simplequery.OrderSimpleQueryDto(o.id, m.name, o.orderDate, o.status, d.address)" +
                        "from Order o"+
                        " join o.member m"+
                        " join o.delivery d", OrderSimpleQueryDto.class)
                .getResultList();
    }
    //실시간으로 대용량 처리 혹은 select 문이 비대할 때 사용
    //address 는 value 타입이라 한번에 가져오기 가능
}
