package com.rasp.server.service;

import com.rasp.server.repo.ConditionSettingsRepository;
import com.rasp.server.repo.tables.Settings;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class ConditionSettingsService {
    private final ConditionSettingsRepository repository;

    Optional<Settings> getConditionSettingsByName(String name) {
        return repository.findById(name);
    }
}
