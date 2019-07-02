package com.rasp.server.repo;

import com.rasp.server.repo.tables.Commands;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.UUID;

public interface CommandLogRepository extends CassandraRepository<Commands, UUID> {
}
