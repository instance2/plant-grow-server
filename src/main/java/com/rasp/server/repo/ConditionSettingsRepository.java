package com.rasp.server.repo;

import com.rasp.server.repo.tables.Settings;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface ConditionSettingsRepository extends CassandraRepository<Settings, String> {
}
