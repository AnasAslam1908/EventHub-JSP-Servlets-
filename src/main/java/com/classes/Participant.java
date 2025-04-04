package com.classes;

import java.sql.Timestamp;

public class Participant {
    private String participantId;
    private String eventId;
    private String userId;
    private Timestamp registeredAt;
    private  String registrationStatus;


    public Participant() {}

    public Participant(String participantId, String eventId, String userId, Timestamp registeredAt) {
        this.participantId = participantId;
        this.eventId = eventId;
        this.userId = userId;
        this.registeredAt = registeredAt;
    }


    public String getParticipantId() {
        return participantId;
    }

    public void setParticipantId(String participantId) {
        this.participantId = participantId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Timestamp getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(Timestamp registeredAt) {
        this.registeredAt = registeredAt;
    }

    public String getRegistrationStatus() {
        return registrationStatus;
    }

    public void setRegistrationStatus(String registrationStatus) {
        this.registrationStatus = registrationStatus;
    }
}

