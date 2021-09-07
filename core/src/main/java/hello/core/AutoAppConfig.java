package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        //ComponentScan 이 일어나는 처음 장소 지정.
        basePackages = "hello.core",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)

//@Component annotation 있는 class 모두 호출, 예제 코드 살리기 위해 Filter 사용.
public class AutoAppConfig {
}
