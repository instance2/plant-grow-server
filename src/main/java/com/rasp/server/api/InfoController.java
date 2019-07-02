package com.rasp.server.api;

import com.rasp.server.constant.EventType;
import com.rasp.server.constant.UnitType;
import com.rasp.server.dto.ParamInfoDto;
import com.rasp.server.repo.tables.Records;
import com.rasp.server.service.EventLogService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class InfoController {
    private EventLogService plantEventLogService;

    @GetMapping("/current/{eventType}")
    public ParamInfoDto getCurrentEvents(@PathVariable EventType eventType) {
        Records value = plantEventLogService.getCurrentRecords(eventType);
        return toDto(value);
    }

    @GetMapping("all")
    public List<ParamInfoDto> getAll() {
        return plantEventLogService.getAllParams().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private ParamInfoDto toDto(Records value) {
        return new ParamInfoDto(EventType.valueOf(value.getParam()), value.getValue(), UnitType.valueOf(value.getUnit()));
    }

}
