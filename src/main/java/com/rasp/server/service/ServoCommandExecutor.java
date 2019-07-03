package com.rasp.server.service;

import com.rasp.server.constant.CommandType;
import com.rasp.server.repo.CommandLogRepository;
import com.rasp.server.repo.tables.Commands;
import lombok.AllArgsConstructor;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
public class ServoCommandExecutor {
    private final CommandLogRepository commandLogRepository;

    public void executeCommand(CommandType commandType) {
        //TODO API ON HARDWARE TBD, DO ASYNC
        System.out.println("GOING EXEC COMMAND " + commandType);
        commandLogRepository.save(new Commands(UUID.randomUUID(), Date.from(Instant.now()).getTime(), commandType.name()));
    }
}
