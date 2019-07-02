package com.rasp.server.service;

import com.rasp.server.constant.CommandType;
import com.rasp.server.constant.EventType;
import com.rasp.server.repo.tables.Params;
import com.rasp.server.repo.tables.Settings;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.Optional;

import static com.rasp.server.constant.EventType.*;

@AllArgsConstructor
public class OptimalConditionService {
    public  final String settingName;
    private final EventLogService eventLogService;
    private final ConditionSettingsService conditionSettingsService;

    public Optional<CommandType> evaluateCommandOnEvent(EventType eventType){
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

    private boolean isInValidRange(BigDecimal value, EventType eventType){
        Settings settings = conditionSettingsService.getConditionSettingsByName(settingName)
                .orElseThrow(()-> new NoSuchElementException("cant find settigns for " + settingName));

        Params param = settings.getParams().stream().filter(p -> p.getParam().equals(eventType.name())).findFirst()
                .orElseThrow(()-> new NoSuchElementException("cant find param for settings " + settingName + eventType.name()));

        return value.compareTo(param.getMin()) > 0 && value.compareTo(param.getMax()) < 0;
    }

    private Optional<CommandType> doCommand(EventType eventType, CommandType notInRange, CommandType inRange) {
        BigDecimal temperature = eventLogService.getCurrentRecords(eventType).getValue();
        if (!isInValidRange(temperature, eventType)) {
            return Optional.of(notInRange);
        }
        return Optional.of(inRange);
    }
}
