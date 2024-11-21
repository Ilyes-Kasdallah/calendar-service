package com.studybuddy.calendar_service.controller;

import com.studybuddy.calendar_service.model.CalendarEvent;
import com.studybuddy.calendar_service.repository.CalendarEventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/calendar")
@CrossOrigin(origins = "http://localhost:4200")  // Enable CORS for Angular
public class CalendarController {
    private static final Logger logger = LoggerFactory.getLogger(CalendarController.class);

    @Autowired
    private CalendarEventRepository calendarEventRepository;

    @GetMapping
    public ResponseEntity<List<CalendarEvent>> getAllEvents() {
        logger.info("Fetching all events");
        List<CalendarEvent> events = calendarEventRepository.findAll();
        logger.info("Found {} events", events.size());
        return ResponseEntity.ok(events);
    }

    @PostMapping
    public ResponseEntity<CalendarEvent> createEvent(@RequestBody CalendarEvent event) {
        logger.info("Creating new event: {}", event);

        // Generate new ID if not provided
        if (event.getId() == null || event.getId().isEmpty()) {
            event.setId(new org.bson.types.ObjectId().toString());
        }

        CalendarEvent savedEvent = calendarEventRepository.save(event);
        logger.info("Event created with ID: {}", savedEvent.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEvent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CalendarEvent> updateEvent(@PathVariable String id, @RequestBody CalendarEvent event) {
        logger.info("Updating event with id {}: {}", id, event);

        if (!calendarEventRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        event.setId(id);
        CalendarEvent updatedEvent = calendarEventRepository.save(event);
        logger.info("Event updated: {}", updatedEvent);
        return ResponseEntity.ok(updatedEvent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable String id) {
        logger.info("Deleting event with id: {}", id);

        if (!calendarEventRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        calendarEventRepository.deleteById(id);
        logger.info("Event deleted");
        return ResponseEntity.noContent().build();
    }
}
