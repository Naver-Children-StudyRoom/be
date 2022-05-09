package nvc.studyroom.user.controller;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import nvc.studyroom.user.dto.LoginInfoDto;
import nvc.studyroom.user.dto.MemberDto;
import nvc.studyroom.user.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signUp")
    public ResponseEntity<LoginInfoDto> signUp(@Parameter(description = "가입 요청 정보") @RequestBody MemberDto memberDto) throws Exception {
        LoginInfoDto loginInfoDto = memberService.signUp(memberDto);
        return new ResponseEntity<>(loginInfoDto, HttpStatus.OK);
    }
}
