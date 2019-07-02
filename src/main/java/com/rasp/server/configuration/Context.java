package com.rasp.server.configuration;

import com.rasp.server.repo.CommandLogRepository;
import com.rasp.server.repo.ConditionSettingsRepository;
import com.rasp.server.repo.EventLogRepository;
import com.rasp.server.service.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Context {

    @Bean
    EventLogService groundDataService(
            EventLogRepository eventLogRepository,
            @Value("${service.windowTimeout.seconds}") long windowTimeout) {
        return new EventLogService(eventLogRepository, windowTimeout);
    }

    @Bean
    OptimalConditionService optimalConditionService(
            @Value("${service.settings.profile}") String settingName,
            EventLogService eventLogService,
            ConditionSettingsService settingsService
    ) {
        return new OptimalConditionService(settingName, eventLogService, settingsService);
    }

    @Bean
    SchedulerCommandService schedulerCommandService(
            OptimalConditionService optimalConditionService,
            ServoCommandExecutor servoCommandExecutor,
            @Value("${service.commands.cron}") String cron) {
        return new SchedulerCommandService(optimalConditionService, servoCommandExecutor, cron);
    }

    @Bean
    ServoCommandExecutor servoCommandExecutor(CommandLogRepository commandLogRepository) {
        return new ServoCommandExecutor(commandLogRepository);
    }

    @Bean
    ConditionSettingsService conditionSettingsService(ConditionSettingsRepository conditionSettingsRepository) {
        return new ConditionSettingsService(conditionSettingsRepository);
    }

    @Bean
    TestUtilService testUtilService(
            EventLogRepository eventLogRepository,
            ConditionSettingsRepository conditionSettingsRepository) {
        return new TestUtilService(eventLogRepository, conditionSettingsRepository);
    }
}
