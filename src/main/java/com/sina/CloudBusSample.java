package com.sina;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.bus.event.RemoteApplicationEvent;
import org.springframework.cloud.bus.event.SentApplicationEvent;
import org.springframework.cloud.bus.jackson.RemoteApplicationEventScan;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@RemoteApplicationEventScan
public class CloudBusSample {

	public static void main(String[] args) {
		SpringApplication.run(CloudBusSample.class, args);
	}

	@EventListener
	public void eventReceived(SentApplicationEvent sentApplicationEvent) {
		System.out.println("bus event sent: " + sentApplicationEvent);
	}

	@EventListener
	public void eventReceived(RemoteApplicationEvent remoteApplicationEvent) {
		System.out.println("bus event received: " + remoteApplicationEvent);
	}

}
