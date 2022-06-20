package nvc.studyroom.member.service;

import lombok.RequiredArgsConstructor;
import nvc.studyroom.member.domain.RedisEmailCheck;
import nvc.studyroom.member.repository.RedisEmailCheckRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RedisEmailCheckService {
    private final RedisEmailCheckRepository redisEmailCheckRepository;

    public void setKey(String email, String code) {
        Optional<RedisEmailCheck> redisEmailCheckOptional = redisEmailCheckRepository.findByEmail(email);
        if(redisEmailCheckOptional.isPresent()) {
            redisEmailCheckOptional.get().setCode(code);
        } else{
            redisEmailCheckRepository.save(RedisEmailCheck.builder().email(email).code(code).build());
        }
    }

    public void checkKey(String email, String code) throws IllegalArgumentException {
        RedisEmailCheck redisEmailCheck = redisEmailCheckRepository.findByEmailAndCode(email, code).orElseThrow(() -> new IllegalArgumentException("no redis"));
    }
}
