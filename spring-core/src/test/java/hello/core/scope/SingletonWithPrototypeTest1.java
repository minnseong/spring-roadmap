package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import static org.assertj.core.api.Assertions.*;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean bean1 = ac.getBean(PrototypeBean.class);
        bean1.addCount();
        assertThat(bean1.getCount()).isEqualTo(1);

        PrototypeBean bean2 = ac.getBean(PrototypeBean.class);
        bean2.addCount();
        assertThat(bean2.getCount()).isEqualTo(1);

        ac.close();
    }

    @Test
    void singletonClientUsePrototype() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        ClientBean bean1 = ac.getBean(ClientBean.class);
        int count1 = bean1.logic();

        ClientBean bean2 = ac.getBean(ClientBean.class);
        int count2 = bean2.logic();

        assertThat(count1).isEqualTo(1);
        assertThat(count2).isEqualTo(2);
    }

    @Test
    void singletonClientUsePrototype2() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean2.class, PrototypeBean.class);

        ClientBean2 bean1 = ac.getBean(ClientBean2.class);
        int count1 = bean1.logic();

        ClientBean2 bean2 = ac.getBean(ClientBean2.class);
        int count2 = bean2.logic();

        assertThat(count1).isEqualTo(1);
        assertThat(count2).isEqualTo(1);
    }

    @Test
    void singletonClientUsePrototype3() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean3.class, PrototypeBean.class);

        ClientBean3 bean1 = ac.getBean(ClientBean3.class);
        int count1 = bean1.logic();

        ClientBean3 bean2 = ac.getBean(ClientBean3.class);
        int count2 = bean2.logic();

        assertThat(count1).isEqualTo(1);
        assertThat(count2).isEqualTo(1);
    }

    @Scope("singleton")
    static class ClientBean {   // ??????????????? ????????? ??????????????? ?????? ???????????? ?????????, ????????? ??????????????? ?????? ???????????? ?????????.

        private final PrototypeBean prototypeBean; // ??????????????? ??????

        @Autowired
        public ClientBean(PrototypeBean prototypeBean) {
            this.prototypeBean = prototypeBean;
        }

        public int logic() {
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
    }

    @Scope("singleton")
    static class ClientBean2 {   // ??????????????? ?????? ????????? ?????????, ????????? ??????????????? ?????? ????????????.

        private ObjectProvider<PrototypeBean> prototypeBeanProvider; // ???????????? ?????????

        @Autowired
        public ClientBean2(ObjectProvider<PrototypeBean> prototypeBeanProvider) {
            this.prototypeBeanProvider = prototypeBeanProvider;
        }

        public int logic() {
            PrototypeBean prototypeBean = prototypeBeanProvider.getObject(); // DL
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
    }

    @Scope("singleton")
    static class ClientBean3 {   // ??????????????? ?????? ????????? ?????????, ????????? ??????????????? ?????? ????????????.

        private Provider<PrototypeBean> prototypeBeanProvider; // ?????? ??????, ????????????????????? ??????.

        @Autowired
        public ClientBean3(Provider<PrototypeBean> prototypeBeanProvider) {
            this.prototypeBeanProvider = prototypeBeanProvider;
        }

        public int logic() {
            PrototypeBean prototypeBean = prototypeBeanProvider.get(); // DL
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init " + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy " + this);
        }
    }
}
