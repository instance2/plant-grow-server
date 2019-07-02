package com.rasp.server.repo;

import com.rasp.server.repo.tables.Records;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;

import java.util.UUID;

public interface EventLogRepository extends ReactiveCassandraRepository<Records, UUID> {
}