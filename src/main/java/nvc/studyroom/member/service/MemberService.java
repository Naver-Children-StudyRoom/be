package nvc.studyroom.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nvc.studyroom.member.domain.Member;
import nvc.studyroom.member.domain.MemberStatusType;
import nvc.studyroom.member.domain.RedisEmailCheck;
import nvc.studyroom.member.dto.LoginDto;
import nvc.studyroom.member.dto.LoginInfoDto;
import nvc.studyroom.member.dto.MemberDto;
import nvc.studyroom.member.repository.MemberRepository;
import nvc.studyroom.member.repository.RedisEmailCheckRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final RedisEmailCheckRepository redisEmailCheckRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signUp(MemberDto memberDto) throws Exception {
        // 1. 이메일 중복체크
        isDuplicatedEmail(memberDto.getEmail());

        // 2. 이메일 인증 여부 체크
        isVerifiedEmail(memberDto.getEmail());

        // 3. 가입 신청 정보 저장
        Member member = Member.builder()
                .email(memberDto.getEmail())
                .password(passwordEncoder.encode(memberDto.getPassword()))
                .name(memberDto.getName())
                .emailReceiveYn(memberDto.getEmailReceiveYn())
                .statusType(MemberStatusType.NORMAL)
                .build();
        member = memberRepository.save(member);
    }

    public LoginInfoDto login(LoginDto loginDto) {
        log.info("call login");
        Member member = memberRepository.findMemberByEmail(loginDto.getEmail()).orElseThrow(() -> new IllegalArgumentException("해당 멤버가 존재하지 않습니다."));
        if(!passwordEncoder.matches(loginDto.getPassword(), member.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return LoginInfoDto.builder()
            .email(member.getEmail())
            .name(member.getName())
            .profileImageUri(member.getProfileImageUri())
            .build();
    }

    private void isDuplicatedEmail(String email) throws Exception {
        Optional<Member> memberByEmail = memberRepository.findMemberByEmail(email);
        if(memberByEmail.isPresent()) {
            throw new Exception("이미 가입된 이메일입니다.");
        }
    }

    private void isVerifiedEmail(String email) throws Exception {
        RedisEmailCheck redisEmailCheck = redisEmailCheckRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("잘못된 접근입니다."));

        if (redisEmailCheck.getVerified().equals(Boolean.FALSE)) {
            throw new IllegalArgumentException("인증되지 않은 이메일입니다.");
        }

        redisEmailCheckRepository.delete(redisEmailCheck);
    }

    public LoginInfoDto getLoginInfo(String id) {
        Member member = memberRepository.findById(Integer.parseInt(id)).orElseThrow(() -> new IllegalArgumentException("해당 멤버가 존재하지 않습니다."));
        return LoginInfoDto.builder()
            .email(member.getEmail())
            .name(member.getName())
            .profileImageUri(member.getProfileImageUri())
            .build();
   }

   @Transactional
   public LoginInfoDto updateMember(String id, LoginInfoDto loginInfoDto) {
       Member member = memberRepository.findById(Integer.parseInt(id)).orElseThrow(() -> new IllegalArgumentException("해당 멤버가 존재하지 않습니다."));
       member.setEmail(loginInfoDto.getEmail());
       member.setName(loginInfoDto.getName());
       member.setProfileImageUri(loginInfoDto.getProfileImageUri());
       return LoginInfoDto.builder()
           .email(member.getEmail())
           .name(member.getName())
           .profileImageUri(member.getProfileImageUri())
           .build();
   }
   
   @Transactional
    public void updatePassword(String id, String password) {
       Member member = memberRepository.findById(Integer.parseInt(id)).orElseThrow(() -> new IllegalArgumentException("해당 멤버가 존재하지 않습니다."));
       member.setPassword(passwordEncoder.encode(password));
   }

    // TODO: 1. accessToken 생성 메소드 생성
    // TODO: 2. accessToken 생성

    // TODO: 1. email 메소드 생성
    // TODO: 2. 인증 번호 랜덤 생성 & 캐시 저장
    // TODO: 3. 인증 번호 email 발송
    // TODO: 4. 인증 번호 email 매칭
}
