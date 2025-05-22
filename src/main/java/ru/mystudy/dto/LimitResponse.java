package ru.mystudy.dto;

import java.math.BigDecimal;

public record LimitResponse(Long userId, BigDecimal dayLimit, BigDecimal balance) {
}
