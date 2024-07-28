package com.yanoos.member.entity.event;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class MapEventEventTypeId implements Serializable {

    @Column(name = "event_id")
    private Long eventId;

    @Column(name = "event_type_id")
    private Long eventTypeId;
}
