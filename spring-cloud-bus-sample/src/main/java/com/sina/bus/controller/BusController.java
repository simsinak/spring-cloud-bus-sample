package com.sina.bus.controller;

import com.sina.bus.event.NotifyEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.bus.BusProperties;
import org.springframework.cloud.bus.event.PathDestinationFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sinaaskarnejad
 */
@RestController
@RequiredArgsConstructor
public class BusController {

    private final ApplicationContext applicationContext;
    private final BusProperties busProperties;
    private final PathDestinationFactory pathDestinationFactory;

    @GetMapping("/send-custom-event")
    public void sendCustomEvent() {
        applicationContext.publishEvent(new NotifyEvent(this, busProperties.getId(), pathDestinationFactory.getDestination(null), "sample data"));
    }
}
