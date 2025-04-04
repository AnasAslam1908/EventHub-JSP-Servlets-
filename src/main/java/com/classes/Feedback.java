package com.classes;


public class Feedback {
    private String feedbackId;
    private String eventId;
    private String userId;
    private int rating;
    private String comments;
    private String submittedAt;

    public Feedback() {}
    public Feedback( String eventId, String userId, int rating, String comments) {

        this.eventId = eventId;
        this.userId = userId;
        this.rating = rating;
        this.comments = comments;
    }



    public String getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(String feedbackId) {
        this.feedbackId = feedbackId;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
    public String getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(String submittedAt) {
        this.submittedAt = submittedAt;
    }
}

