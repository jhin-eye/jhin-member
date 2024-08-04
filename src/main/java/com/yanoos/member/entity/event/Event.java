package com.yanoos.member.entity.event;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "event")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;

    @Column(name = "event_data", columnDefinition = "json", nullable = false)
    private String eventData;

    @Column(name = "published", nullable = false)
    private Boolean published;

    @Column(name = "event_type", nullable = false)
    private String eventType;

    public void done() {
        this.published =true;
    }
}