package com.rasp.server.service;

import com.rasp.server.constant.EventType;
import com.rasp.server.constant.UnitType;
import com.rasp.server.repo.EventLogRepository;
import com.rasp.server.repo.ConditionSettingsRepository;
import com.rasp.server.repo.tables.Params;
import com.rasp.server.repo.tables.Records;
import com.rasp.server.repo.tables.Settings;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

import static com.rasp.server.constant.EventType.*;

@AllArgsConstructor
public class TestUtilService {
    private final EventLogRepository eventLogRepository;
    private final ConditionSettingsRepository conditionSettingsRepository;

    public void setMockData(EventType eventType) {
        String unit = getUnitType(eventType);
        IntStream.range(0, 20000)
                .forEach(index ->
                        CompletableFuture.runAsync(
                                () -> eventLogRepository.save(
                                        new Records(UUID.randomUUID(), Date.from(Instant.now()).getTime(), eventType.name(), new BigDecimal(Math.random()), unit)
                                ).block()
                        ));
    }

    public void setSettings() {
        Set<Params> paramSet = new HashSet<>();
        paramSet.add(new Params(HUMIDITY.name(), BigDecimal.valueOf(33), BigDecimal.valueOf(66), UnitType.PERCENTS.name(), true));
        paramSet.add(new Params(LIGHT.name(), BigDecimal.valueOf(65000), BigDecimal.valueOf(66000), UnitType.ILLUMINES.name(), true));
        paramSet.add(new Params(TEMPERATURE.name(), BigDecimal.valueOf(25), BigDecimal.valueOf(28), UnitType.CELSIUS.name(), true));
        Settings settings = new Settings("BAMBOO", 1,
                paramSet,
                true);
        conditionSettingsRepository.save(settings);
    }

    private String getUnitType(EventType eventType) {
        String unit;
        switch (eventType) {
            case LIGHT:
                unit = UnitType.ILLUMINES.name();
                break;
            case HUMIDITY:
                unit = UnitType.PERCENTS.name();
                break;
            case TEMPERATURE:
                unit = UnitType.CELSIUS.name();
                break;
            default:
                unit = null;
        }
        return unit;
    }
}
