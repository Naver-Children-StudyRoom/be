package nvc.studyroom.member.service;

import nvc.studyroom.member.domain.RedisEmailCheck;
import nvc.studyroom.member.repository.RedisEmailCheckRepository;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import nvc.studyroom.common.utils.MailUtils;
import nvc.studyroom.common.utils.TempKey;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender mailSender;
    private final RedisEmailCheckService redisEmailCheckService;

    @Transactional
    public void register(String email) throws Exception {
        String key = TempKey.generate();
        MailUtils sendMail = new MailUtils(mailSender);
        sendMail.setSubject("[Study Room 인증메일 입니다.]"); //메일제목
        sendMail.setText(
            "<h1>메일인증</h1>" +
                "<br/>아래 인증 번호를 입력해주세요"+
                "<br/>인증 번호" + key);
        sendMail.setFrom("naverChildren@gmail.com", "[발송자 이름]");
        sendMail.setTo(email);
//        sendMail.send();

        redisEmailCheckService.setKey(email, key);
    }
}
