package org.ioc.jb8pigeonskyrace.events;

import lombok.Getter;
import org.ioc.jb8pigeonskyrace.dtos.RaceDTO;
import org.springframework.context.ApplicationEvent;

@Getter
public class RaceClosedEvent extends ApplicationEvent {
    private final RaceDTO race;
    public RaceClosedEvent(Object source, RaceDTO race) {
        super(source);
        this.race = race;
    }
}
