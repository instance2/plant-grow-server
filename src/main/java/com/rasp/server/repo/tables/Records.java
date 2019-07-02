package com.rasp.server.repo.tables;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
public class Records {
    @Id
    private UUID id;
    private long timestamp;
    private String param;
    private BigDecimal value;
    private String unit;
}
