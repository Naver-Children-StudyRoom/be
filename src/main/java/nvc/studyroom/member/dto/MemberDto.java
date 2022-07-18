package nvc.studyroom.member.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MemberDto {
    private String email;

    private String password;

    private String name;

    private Boolean emailReceiveYn;
}
