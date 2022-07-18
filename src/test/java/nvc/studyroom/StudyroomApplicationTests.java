package nvc.studyroom;

import nvc.studyroom.member.controller.MemberController;
import nvc.studyroom.member.dto.LoginDto;
import nvc.studyroom.member.dto.LoginInfoDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class StudyroomApplicationTests {

	@Autowired
	private MemberController memberController;

	@Test
	void contextLoads() {
	}

	@Test
	void signupTest() {
		try {
			ResponseEntity<LoginInfoDto> responseEntity = memberController.login(new LoginDto("admin@gmail.com", "admin"));
			System.out.println(responseEntity);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
