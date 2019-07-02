package com.rasp.server.repo.tables;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
@UserDefinedType
public class Params {
    @Id
    private String param;
    private BigDecimal min;
    private BigDecimal max;
    private String unit;
    private boolean active;
}