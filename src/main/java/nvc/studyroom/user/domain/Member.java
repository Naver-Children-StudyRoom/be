package nvc.studyroom.user.domain;

import lombok.*;
import nvc.studyroom.common.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@DynamicUpdate
@DynamicInsert
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Email
    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String name;

    @Enumerated(value = EnumType.STRING)
    private MemberStatusType statusType;

    private Integer loginFailCount;

    private Boolean emailReceiveYn;

    private String profileImageUri;
}