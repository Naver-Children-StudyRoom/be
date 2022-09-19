package nvc.studyroom.studyroom.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import nvc.studyroom.studyroom.domain.StudyRoomTopicType;

@AllArgsConstructor
@Getter
@Setter
public class StudyRoomDto {

    @NotNull
    private String title;

    private String password;

    @NotNull
    private Integer maxParticipantCount;

    private List<String> hashTag;

    @NotNull
    private StudyRoomTopicType topic;
}
