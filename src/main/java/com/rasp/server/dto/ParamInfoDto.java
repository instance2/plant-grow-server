package com.rasp.server.dto;

import com.rasp.server.constant.EventType;
import com.rasp.server.constant.UnitType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
public class ParamInfoDto {
    private EventType param;
    private BigDecimal value;
    private UnitType unitType;
}
