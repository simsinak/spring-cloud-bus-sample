package com.sina.bus.event;

import org.springframework.cloud.bus.event.Destination;
import org.springframework.cloud.bus.event.RemoteApplicationEvent;
import org.springframework.core.style.ToStringCreator;

import java.util.Objects;

/**
 * @author sinaaskarnejad
 */
public class NotifyEvent extends RemoteApplicationEvent {

    private String data;

    public NotifyEvent() {

    }

    public NotifyEvent(Object source, String originService, Destination destination, String data) {
        super(source, originService, destination);
        this.data = data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        NotifyEvent that = (NotifyEvent) o;
        return Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), data);
    }

    @Override
    public String toString() {
        return new ToStringCreator(this).append("id", getId()).append("originService", getOriginService())
                .append("destinationService", getDestinationService())
                .append("data", data).toString();
    }
}
