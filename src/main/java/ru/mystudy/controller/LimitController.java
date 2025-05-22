package ru.mystudy.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.mystudy.dto.LimitExecuteRequest;
import ru.mystudy.dto.LimitResponse;
import ru.mystudy.dto.LimitRestoreRequest;
import ru.mystudy.service.LimitService;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LimitController {
    private final LimitService limitService;

    @GetMapping("/limit/user/{id}")
    public LimitResponse getLimitByUserId(@PathVariable("id") Long userId) {
        log.info("get, method: getLimitByUserId, userId - {}", userId);
        return limitService.getLimitByUserId(userId);
    }

    @PostMapping("/limit/execute")
    public LimitResponse executeLimit(@RequestBody LimitExecuteRequest limitExecuteRequest) {
        log.info("post, method: executeLimit, ExecuteLimitRequest - {}", limitExecuteRequest);
        return limitService.executeLimit(limitExecuteRequest);
    }

    @PostMapping("/limit/restore")
    public LimitResponse restoreLimit(@RequestBody LimitRestoreRequest limitRestoreRequest) {
        log.info("post, method: restoreLimit, RestoreLimitRequest - {}", limitRestoreRequest);
        return limitService.restoreLimit(limitRestoreRequest);
    }
}
