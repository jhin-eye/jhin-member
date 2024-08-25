package com.yanoos.member.repository.event;


import com.yanoos.member.entity.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event,Long> {
    List<Event> findByPublishedOrderByIdAsc(boolean b);
}
