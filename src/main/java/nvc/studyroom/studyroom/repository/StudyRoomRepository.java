package nvc.studyroom.studyroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nvc.studyroom.studyroom.domain.StudyRoom;

@Repository
public interface StudyRoomRepository extends JpaRepository<StudyRoom, Long> {

}
