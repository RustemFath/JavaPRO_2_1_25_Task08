package ru.mystudy.utils;

import org.springframework.stereotype.Component;
import ru.mystudy.dto.LimitResponse;
import ru.mystudy.entity.LimitEntity;

import java.math.BigDecimal;

@Component
public class LimitMapper {

    public LimitResponse toDto(LimitEntity limit) {
        return new LimitResponse(limit.getUserId(), limit.getDayLimit(), limit.getBalance());
    }

    public LimitEntity toEntity(Long userId, BigDecimal dayLimit) {
        return new LimitEntity(userId, dayLimit, dayLimit);
    }
}
