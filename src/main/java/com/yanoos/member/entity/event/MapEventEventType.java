package com.yanoos.member.entity.event;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "map_event_event_type")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MapEventEventType {

    @EmbeddedId
    private MapEventEventTypeId id;

    @ManyToOne
    @MapsId("eventId")
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @ManyToOne
    @MapsId("eventTypeId")
    @JoinColumn(name = "event_type_id", nullable = false)
    private EventType eventType;

    @Column(name = "published", nullable = false)
    private Boolean published;
}
