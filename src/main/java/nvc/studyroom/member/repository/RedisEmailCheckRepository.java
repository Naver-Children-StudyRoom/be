package nvc.studyroom.member.repository;

import nvc.studyroom.member.domain.RedisEmailCheck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RedisEmailCheckRepository extends JpaRepository<RedisEmailCheck, Integer> {
    Optional<RedisEmailCheck> findByEmail(String email);
    Optional<RedisEmailCheck> findByEmailAndKey(String email, String key);
}
