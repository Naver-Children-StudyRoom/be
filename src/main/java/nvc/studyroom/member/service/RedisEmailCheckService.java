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

    public void setKey(String email, String key) {
        Optional<RedisEmailCheck> redisEmailCheckOptional = redisEmailCheckRepository.findByEmail(email);
        if(redisEmailCheckOptional.isPresent()) {
            redisEmailCheckOptional.get().setKey(key);
        } else{
            redisEmailCheckRepository.save(RedisEmailCheck.builder().email(email).key(key).build());
        }
    }

    public void checkKey(String email, String key) throws IllegalArgumentException {
        RedisEmailCheck redisEmailCheck = redisEmailCheckRepository.findByEmailAndKey(email, key).orElseThrow(() -> new IllegalArgumentException("no redis"));
    }
}
