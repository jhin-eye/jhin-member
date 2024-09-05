package com.yanoos.global.entity.event;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "event")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "parent_event_id", nullable = true)
    private Long parentEventId;

    @Column(name = "event_data", columnDefinition = "json", nullable = false)
    private String eventData;

    @Column(name = "published", nullable = false)
    private Boolean published;

    @Column(name = "event_type", nullable = false)
    private String eventType;

    @Column(name = "created_at", nullable = false)
    private Long createdAt;
    @Column(name="try_count", nullable = false)
    private Long tryCount;
    @Column(name = "published_at")
    private Long publishedAt;

    public void done() {
        this.published =true;
    }
}