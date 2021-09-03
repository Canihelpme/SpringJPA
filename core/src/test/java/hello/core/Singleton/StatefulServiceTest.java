package hello.core.Singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.assertThat;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService ss1 = ac.getBean(StatefulService.class);
        StatefulService ss2 = ac.getBean(StatefulService.class);

        //Thread A 10000원 order
        int userAPrice = ss1.order("userA", 10000);
        //Thread B 20000원 order
        int userBPrice = ss2.order("userB", 20000);

//        int price = ss1.getPrice();
        System.out.println("userAPrice = " + userAPrice);
        //출력직전 ss2 order에 의해 20000원이 출력됨.
//        assertThat(ss1.getPrice()).isEqualTo(20000);
    }
    static class TestConfig{

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}
