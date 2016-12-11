package ua.peresvit.service.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.peresvit.dao.VerificationTokenRepository;

import java.time.Instant;
import java.util.Date;

@Service
@Transactional
public class TokensPurgeTask {

    private final VerificationTokenRepository tokenRepository;

    @Autowired
    public TokensPurgeTask(VerificationTokenRepository tokenRepository, Environment env) {
        this.tokenRepository = tokenRepository;
    }

    @Scheduled(cron = "${purge.cron.expression}")
    public void purgeExpired() {

        Date now = Date.from(Instant.now());

        tokenRepository.deleteAllExpiredSince(now);
    }
}
