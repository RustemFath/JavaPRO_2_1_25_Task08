package ru.mystudy.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.math.BigDecimal;

@ConfigurationProperties(prefix = "limits")
@Setter
@Getter
public class LimitsProperties {
    private BigDecimal dayLimit;
    private String scheduledCronExpression;
}
