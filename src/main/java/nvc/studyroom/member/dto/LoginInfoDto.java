package nvc.studyroom.member.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginInfoDto {
    private String email;

    private String name;

    private String profileImageUri;
}
