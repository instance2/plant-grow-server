package com.rasp.server.repo.tables;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
public class Commands {
    @Id
    private UUID id;
    private long timestamp;
    private String command;
}
