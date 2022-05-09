package nvc.studyroom.member.service;

import lombok.RequiredArgsConstructor;
import nvc.studyroom.member.domain.Member;
import nvc.studyroom.member.domain.MemberStatusType;
import nvc.studyroom.member.dto.LoginInfoDto;
import nvc.studyroom.member.dto.MemberDto;
import nvc.studyroom.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public LoginInfoDto signUp(MemberDto memberDto) throws Exception {
        // 1. 이메일 중복체크
        isDuplicatedEmail(memberDto.getEmail());

        // 2. 가입 신청 정보 저장
        Member member = Member.builder()
                .email(memberDto.getEmail())
                .password(memberDto.getPassword())
                .name(memberDto.getName())
                .emailReceiveYn(memberDto.getEmailReceiveYn())
                .statusType(MemberStatusType.NORMAL)
                .build();
        member = memberRepository.save(member);

        return LoginInfoDto.builder()
                .email(member.getEmail())
                .name(member.getName())
                .profileImageUri(member.getProfileImageUri())
                .build();
    }

    @Transactional
    public void isDuplicatedEmail(String email) throws Exception{
        Optional<Member> memberByEmail = memberRepository.findMemberByEmail(email);
        if(memberByEmail.isPresent()) {
            throw new Exception("이미 가입된 이메일입니다.");
        }
    }

    // TODO: 1. accessToken 생성 메소드 생성
    // TODO: 2. accessToken 생성

    // TODO: 1. email 메소드 생성
    // TODO: 2. 인증 번호 랜덤 생성 & 캐시 저장
    // TODO: 3. 인증 번호 email 발송
    // TODO: 4. 인증 번호 email 매칭
}
