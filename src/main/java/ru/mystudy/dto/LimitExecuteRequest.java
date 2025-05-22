package ru.mystudy.dto;

import java.math.BigDecimal;

public record LimitExecuteRequest(Long userId, BigDecimal limit) {
}
