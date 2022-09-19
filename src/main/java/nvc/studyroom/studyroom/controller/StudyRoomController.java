package nvc.studyroom.studyroom.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import io.swagger.v3.oas.annotations.Parameter;
import nvc.studyroom.studyroom.dto.StudyRoomDto;
import nvc.studyroom.studyroom.service.StudyRoomService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/studyroom")
public class StudyRoomController {

    private final StudyRoomService studyRoomService;

    @PostMapping
    public ResponseEntity<Boolean> createStudyRoom(@Parameter(description = "스터디룸 생성 정보") @Valid @RequestBody StudyRoomDto studyRoomDto) throws Exception {
        studyRoomService.createStudyRoom(studyRoomDto);
        return ResponseEntity.of(Optional.of(true));
    }
}
