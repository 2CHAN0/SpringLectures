package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import javax.swing.text.html.Option;
import java.util.Optional;

public class AutowiredTest {
    @Test
    void AutowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);

    }

    static class TestBean{
        @Autowired(required = false)
        public void setNoBean1(Member member) {
            //스프링 빈에 없는 거 집어넣음,
            //주입할 대상(의존관계) 없으면 메서드 자체가 호출이 안됨
            System.out.println("member = " + member);
        }
        @Autowired
        public void setNoBean2(@Nullable Member member) {
            //주입할 대상 없으면 null을 넣어줌
            System.out.println("member2 = " + member);
        }
        @Autowired
        public void setNoBean3(Optional<Member> member) {
            //주입할 대상 없으면 Optional.empty 객체를 넣어줌(java8)
            System.out.println("member3 = " + member);
        }
    }

}
