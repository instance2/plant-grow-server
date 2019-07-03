package com.rasp.server.service;

import com.rasp.server.constant.CommandType;
import com.rasp.server.constant.EventType;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.Optional;

import static com.rasp.server.constant.EventType.*;

@AllArgsConstructor
public class OptimalConditionService {
    private final EventLogService eventLogService;
    private final ConditionSettingsService conditionSettingsService;

    public Optional<CommandType> evaluateCommandOnEvent(EventType eventType) {
        switch (eventType) {
            case LIGHT:
                return doCommand(LIGHT, CommandType.LIGHT_ON, CommandType.LIGHT_OFF);
            case TEMPERATURE:
                return doCommand(TEMPERATURE, CommandType.HEAT, CommandType.WAIT);
            case HUMIDITY:
                return doCommand(HUMIDITY, CommandType.HYDRATION, CommandType.WAIT);
            default:
                throw new IllegalArgumentException("Unknown event Type " + eventType.name());
        }
    }

    private boolean isInValidRange(BigDecimal value, EventType eventType) {
        return conditionSettingsService.getActiveParams().stream()
                .filter(p -> p.getParam().equals(eventType.name())).findFirst()
                .map(params -> value.compareTo(params.getMin()) > 0 && value.compareTo(params.getMax()) < 0)
                .orElse(Boolean.TRUE);

    }

    private Optional<CommandType> doCommand(EventType eventType, CommandType notInRange, CommandType inRange) {
        BigDecimal temperature = eventLogService.getCurrentRecords(eventType).getValue();
        if (!isInValidRange(temperature, eventType)) {
            return Optional.of(notInRange);
        }
        return Optional.of(inRange);
    }
}
