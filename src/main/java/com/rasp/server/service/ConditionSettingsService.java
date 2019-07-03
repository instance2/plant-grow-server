package com.rasp.server.service;

import com.rasp.server.repo.ConditionSettingsRepository;
import com.rasp.server.repo.tables.Params;
import com.rasp.server.repo.tables.Settings;
import lombok.AllArgsConstructor;

import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
public class ConditionSettingsService {
    private final ConditionSettingsRepository repository;
    public final String settingName;

    Set<Params> getActiveParams() {
        return repository.findById(settingName)
                .filter(Settings::isActive)
                .map(Settings::getParams)
                .map(params -> params.stream().filter(Params::isActive).collect(Collectors.toSet()))
                .filter(set -> !set.isEmpty())
                .orElseThrow(() -> new NoSuchElementException(String.format("No Active Settings for %s ", settingName)));
    }
    //TODO CONVERT PARAM TO DOMAIN OBJ
}
