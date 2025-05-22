package ru.mystudy.dto;

import java.math.BigDecimal;

public record LimitRestoreRequest(Long userId, BigDecimal limit) {
}
