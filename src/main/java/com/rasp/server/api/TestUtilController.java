package com.rasp.server.api;

import com.rasp.server.constant.EventType;
import com.rasp.server.service.TestUtilService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TestUtilController {
    private TestUtilService testUtilService;

    @GetMapping("/test/populate/{eventType}")
    public void populateEvents(@PathVariable EventType eventType) {
        testUtilService.setMockData(eventType);
    }

    @GetMapping("/test/populate/settings")
    public void populateSettings() {
        testUtilService.setSettings();
    }
}
