package First.Hellospring.repository;

import First.Hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

/**
 * Interface를 통해 기본적인 method의 형태만 선언.
 * Method를 상속받은 class에서 method body의 내용 채워서 사용.
 */

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    //Optional을 통해 null 값으로 반환될 때 null값 그대로 반환하는 대신 Optional로 감싸서 반환.
    List<Member> findAll();
}
