package ru.mystudy.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "limits")
public class LimitEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "day_limit")
    private BigDecimal dayLimit;

    @Column(name = "balance")
    private BigDecimal balance;

    public LimitEntity(Long userId, BigDecimal dayLimit, BigDecimal balance) {
        this.userId = userId;
        this.dayLimit = dayLimit;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "LimitEntity{" +
                "id=" + id +
                ", userId=" + userId +
                ", dayLimit=" + dayLimit +
                ", balance=" + balance +
                '}';
    }
}
