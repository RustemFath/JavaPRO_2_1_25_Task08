package ru.mystudy.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.mystudy.config.LimitsProperties;
import ru.mystudy.dto.LimitExecuteRequest;
import ru.mystudy.dto.LimitResponse;
import ru.mystudy.dto.LimitRestoreRequest;
import ru.mystudy.entity.LimitEntity;
import ru.mystudy.repository.LimitRepository;
import ru.mystudy.utils.LimitMapper;

@Slf4j
@Service
@RequiredArgsConstructor
public class LimitService {
    private final LimitRepository limitRepository;
    private final CheckLimitService checkLimitService;
    private final LimitMapper limitMapper;
    private final LimitsProperties properties;

    public LimitResponse getLimitByUserId(Long userId) {
        return limitMapper.toDto(this.getLimitEntityByUserId(userId));
    }

    private LimitEntity getLimitEntityByUserId(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("Parameter userId is null");
        }
        return limitRepository.findByUserId(userId)
                .orElseGet(() -> this.createLimitByUserId(userId));
    }

    public LimitResponse executeLimit(LimitExecuteRequest limitExecuteRequest) {
        LimitEntity limitEntity = this.getLimitEntityByUserId(limitExecuteRequest.userId());
        checkLimitService.checkExecuteLimit(limitExecuteRequest, limitEntity);
        limitEntity.setBalance(limitEntity.getBalance().subtract(limitExecuteRequest.limit()));
        limitRepository.save(limitEntity);
        return limitMapper.toDto(limitEntity);
    }

    public LimitResponse restoreLimit(LimitRestoreRequest limitRestoreRequest) {
        LimitEntity limitEntity = this.getLimitEntityByUserId(limitRestoreRequest.userId());
        checkLimitService.checkRestoreLimit(limitRestoreRequest, limitEntity);
        limitEntity.setBalance(limitEntity.getBalance().add(limitRestoreRequest.limit()));
        limitRepository.save(limitEntity);
        return limitMapper.toDto(limitEntity);
    }

    private LimitEntity createLimitByUserId(Long userId) {
        if (userId < 0 || userId > 100) {
            throw new IllegalArgumentException("Parameter userId out of range 1..100");
        }
        return limitRepository.save(limitMapper.toEntity(userId, properties.getDayLimit()));
    }

    @Scheduled(cron = "${limits.scheduled-cron-expression}", zone = "Europe/Moscow")
    public void resetLimit() {
        log.info("Сброс лимитов по всем клиентам");
        limitRepository.updateDayLimitAndBalanceAll(properties.getDayLimit());
    }
}
