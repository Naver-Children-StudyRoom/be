package nvc.studyroom.member.controller;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import nvc.studyroom.member.dto.LoginInfoDto;
import nvc.studyroom.member.dto.MemberDto;
import nvc.studyroom.member.service.MailService;
import nvc.studyroom.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MailService mailService;

    @PostMapping("/signUp")
    public ResponseEntity<LoginInfoDto> signUp(@Parameter(description = "가입 요청 정보") @RequestBody MemberDto memberDto) throws Exception {
        LoginInfoDto loginInfoDto = memberService.signUp(memberDto);
        return new ResponseEntity<>(loginInfoDto, HttpStatus.OK);
    }

    @GetMapping("/valid-email")
    public ResponseEntity<Boolean> validEmail(@Parameter(description = "이메일") @RequestParam String email) throws Exception {
        // 중복체크
        memberService.isDuplicatedEmail(email);
        // 중복 아니면 메일 발송
        mailService.register(email);

        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
    }
}
