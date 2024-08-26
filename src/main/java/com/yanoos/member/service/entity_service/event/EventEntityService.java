package com.yanoos.member.service.entity_service.event;

import com.yanoos.global.entity.event.Event;
import com.yanoos.member.repository.event.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EventEntityService {
    private final EventRepository eventRepository;

    public List<Event> getEventsByPublished(Boolean published) {
        return eventRepository.findByPublishedOrderByIdAsc(published);
    }

    @Transactional
    public void save(Event event) {
        eventRepository.save(event);
    }
}
