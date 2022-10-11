package nvc.studyroom.studyroom.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.AttributeConverter;
import javax.persistence.Convert;
import javax.persistence.Converter;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.boot.context.properties.bind.DefaultValue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import nvc.studyroom.common.BaseEntity;

@Getter
@Setter
@Entity
@DynamicUpdate
@DynamicInsert
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudyRoom extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer memberId;

    @NotNull
    private String title;

    private String password;

    // TODO : Chat entity 만들면 고민해보기
    private Integer notice;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private StudyRoomType type;

    @NotNull
    private Integer maxParticipantCount;

    @NotNull
    @Builder.Default
    private Integer currentParticipantCount = 1;

    @Convert(converter = HashTagConverter.class)
    private List<String> hashTag;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private StudyRoomTopicType topic;
}