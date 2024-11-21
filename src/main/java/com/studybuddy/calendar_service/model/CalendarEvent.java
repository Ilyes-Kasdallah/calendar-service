package com.studybuddy.calendar_service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "calendar_events")
public class CalendarEvent {
    @Id
    private String id;
    private String text;      // matches scheduler's text field
    private String start_date; // matches scheduler's start_date field
    private String end_date;   // matches scheduler's end_date field

    public CalendarEvent() {}

    public CalendarEvent(String text, String start_date, String end_date) {
        this.text = text;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    @Override
    public String toString() {
        return "CalendarEvent{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", start_date='" + start_date + '\'' +
                ", end_date='" + end_date + '\'' +
                '}';
    }
}