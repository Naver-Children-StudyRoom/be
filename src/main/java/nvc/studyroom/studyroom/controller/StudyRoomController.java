package nvc.studyroom.studyroom.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Boolean> createStudyRoom(@RequestAttribute int id, @Parameter(description = "스터디룸 생성 정보") @Valid @RequestBody StudyRoomDto studyRoomDto) throws Exception {
        studyRoomService.createStudyRoom(id, studyRoomDto);
        return ResponseEntity.of(Optional.of(true));
    }
}
