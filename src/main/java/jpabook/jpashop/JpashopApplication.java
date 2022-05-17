package jpabook.jpashop;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JpashopApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpashopApplication.class, args);
	}

	// -> 엔티티를 바로 노출할 때 써야함.
	@Bean
	Hibernate5Module hibernate5Module() {
//		hibernate5Module.configure(Hibernate5Module.Feature.FORCE_LAZY_LOADING, true);
//		return hibernate5Module;
		return new Hibernate5Module();
	}
}
