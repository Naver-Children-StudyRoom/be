package nvc.studyroom.studyroom.service;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import nvc.studyroom.studyroom.domain.StudyRoom;
import nvc.studyroom.studyroom.domain.StudyRoomType;
import nvc.studyroom.studyroom.dto.StudyRoomDto;
import nvc.studyroom.studyroom.repository.StudyRoomRepository;

@Service
@RequiredArgsConstructor
public class StudyRoomService {
    private final StudyRoomRepository studyRoomRepository;

    public void createStudyRoom(StudyRoomDto studyRoomDto) {
        studyRoomRepository.save(StudyRoom.builder()
            .memberId(null)
            .title(studyRoomDto.getTitle())
            .password(studyRoomDto.getPassword())
            .type(StringUtils.isBlank(studyRoomDto.getPassword()) ? StudyRoomType.OPEN : StudyRoomType.SECRET)
            .maxParticipantCount(studyRoomDto.getMaxParticipantCount())
            .hashTag(studyRoomDto.getHashTag())
            .topic(studyRoomDto.getTopic())
            .build());
    }
}
