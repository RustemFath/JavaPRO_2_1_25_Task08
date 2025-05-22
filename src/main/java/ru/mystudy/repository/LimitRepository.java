package ru.mystudy.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.mystudy.entity.LimitEntity;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface LimitRepository extends JpaRepository<LimitEntity, Long> {
    Optional<LimitEntity> findByUserId(Long userId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Limits SET day_limit = ?1, balance = ?1", nativeQuery = true)
    void updateDayLimitAndBalanceAll(BigDecimal dayLimit);
}
