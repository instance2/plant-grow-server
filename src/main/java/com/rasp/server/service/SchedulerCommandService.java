package com.rasp.server.service;

import com.rasp.server.constant.EventType;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Optional;
import java.util.stream.Stream;

@AllArgsConstructor
public class SchedulerCommandService {
    private final OptimalConditionService conditionService;
    private final ServoCommandExecutor commandExecutor;
    private final String cron;

    @Scheduled(cron = "${service.commands.cron}")
    public void scheduleTaskWithCronExpression() {
        System.out.println("EXEC COMMANDS");
        Stream.of(conditionService.evaluateCommandOnEvent(EventType.LIGHT),
                conditionService.evaluateCommandOnEvent(EventType.TEMPERATURE),
                conditionService.evaluateCommandOnEvent(EventType.HUMIDITY))
                .peek(System.out::println)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach(commandExecutor::executeCommand);
    }

    private String getCronConfig() {
        return cron;
    }

}
