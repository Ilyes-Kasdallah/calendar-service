package com.studybuddy.calendar_service.repository;

import com.studybuddy.calendar_service.model.CalendarEvent;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CalendarEventRepository extends MongoRepository<CalendarEvent, String> {
}