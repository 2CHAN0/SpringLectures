package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

public class SingletonWithPrototypeTest1 {
    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        Assertions.assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        Assertions.assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }

    @Test
    void singletonClientUsePrototype() {
        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
        ClientBean clentBean1 = ac.getBean(ClientBean.class);
        int count1 = clentBean1.logic();
        Assertions.assertThat(count1).isEqualTo(1);

        ClientBean clentBean2 = ac.getBean(ClientBean.class);
        int count2 = clentBean2.logic();
        Assertions.assertThat(count2).isEqualTo(1);

    }

    @Scope("singleton")
    static class ClientBean {
//        private final PrototypeBean prototypeBean;

        @Autowired
//        private ObjectProvider<PrototypeBean> prototypeBeanObjectProvider;
        private Provider<PrototypeBean> prototypeBeanObjectProvider;

          //생성 시점에 프로토타입 빈이 주입. 그런데 ClientBean은 싱글톤이므로 이때 받은 걸 계속 사용.
//        @Autowired
//        ApplicationContext applicationContext;

//        @Autowired
//        public ClientBean(PrototypeBean prototypeBean) {
//            this.prototypeBean = prototypeBean;
//        }

        public int logic() {
            //이렇게 하면 logic 호출할 때마다 빈컨테이너에게 ProtoType Bean 달라고 요청해서 매번 1개씩 만들 수는 있음
//            PrototypeBean prototypeBean = applicationContext.getBean(PrototypeBean.class);
//            PrototypeBean prototypeBean = prototypeBeanObjectProvider.getObject();
            PrototypeBean prototypeBean = prototypeBeanObjectProvider.get();
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    @Scope("prototype")
    static class PrototypeBean{
        private int count = 0;
        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }
        @PostConstruct
        public void init(){
            System.out.println("Init "+ this);
        }
        @PreDestroy
        public void destroy() {
            System.out.println("Destroy");
        }
    }
}
