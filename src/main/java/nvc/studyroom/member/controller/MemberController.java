package nvc.studyroom.member.controller;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;

import nvc.studyroom.member.dto.LoginDto;
import nvc.studyroom.member.dto.LoginInfoDto;
import nvc.studyroom.member.dto.MemberDto;
import nvc.studyroom.member.service.MailService;
import nvc.studyroom.member.service.MemberService;
import nvc.studyroom.security.jwt.JwtAuthenticationFilter;
import nvc.studyroom.security.jwt.JwtTokenProvider;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @PostMapping("/signup")
    public ResponseEntity<LoginInfoDto> signUp(@Parameter(description = "가입 요청 정보") @RequestBody MemberDto memberDto) throws Exception {
        memberService.signUp(memberDto);
        return login(new LoginDto(memberDto.getEmail(), memberDto.getPassword()));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginInfoDto> login(@Parameter(description = "가입 요청 정보") @RequestBody LoginDto loginDto) throws Exception {
        LoginInfoDto loginInfoDto = memberService.login(loginDto);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtTokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtAuthenticationFilter.AUTHORIZATION_HEADER, "Bearer" + jwt);

        return new ResponseEntity<>(loginInfoDto, httpHeaders, HttpStatus.OK);
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
