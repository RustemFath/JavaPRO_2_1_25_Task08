package ru.mystudy.service;

import org.springframework.stereotype.Service;
import ru.mystudy.dto.LimitExecuteRequest;
import ru.mystudy.dto.LimitRestoreRequest;
import ru.mystudy.entity.LimitEntity;
import ru.mystudy.exception.CheckLimitException;

@Service
public class CheckLimitService {

    public void checkExecuteLimit(LimitExecuteRequest request, LimitEntity limitEntity) {
        if (limitEntity.getBalance().compareTo(request.limit()) < 0) {
            throw new CheckLimitException("Сумма баланса (" + limitEntity.getBalance() +
                    ") недостаточна, request.limit = " + request.limit());
        }
    }

    public void checkRestoreLimit(LimitRestoreRequest request, LimitEntity limitEntity) {
        if (limitEntity.getBalance().add(request.limit()).compareTo(limitEntity.getDayLimit()) > 0) {
            throw new CheckLimitException("Сумма баланса (" + limitEntity.getBalance().add(request.limit()) +
                    ") после восстановления лимита превысит дневной лимит (" + limitEntity.getDayLimit() +
                    "), request.limit = " + request.limit());
        }
    }
}
