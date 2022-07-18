package nvc.studyroom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@EnableJpaAuditing
@SpringBootApplication
public class StudyroomApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudyroomApplication.class, args);
	}

}
