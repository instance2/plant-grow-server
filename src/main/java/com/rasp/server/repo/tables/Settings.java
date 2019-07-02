package com.rasp.server.repo.tables;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Set;

@Builder
@Data
@AllArgsConstructor
public class Settings {
    @Id
    private String id;
    private long timestamp;
    private Set<Params> params;
    private boolean active;

}
