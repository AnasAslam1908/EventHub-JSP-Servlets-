package com.classes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.database.DatabaseUtil;

public class UserService {

    public boolean registerUser(User user) {
        boolean result = false;
        String sql = "INSERT INTO Users (user_id, username, email, password) VALUES (?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, generateUserId());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPassword());


            int rowsAffected = stmt.executeUpdate();
            conn.close();
            result = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
    public boolean checkCapacity(String eventId){
        boolean result = false;
        String sql = "SELECT * FROM Events WHERE event_id = ? and registered_count >= capacity";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            conn = DatabaseUtil.getConnection();
            stmt=conn.prepareStatement(sql);
            stmt.setString(1, eventId);
            rs=stmt.executeQuery();
            if(rs.next()){
                result = true;
            }
            conn.close();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean isUsernameTaken(String username) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            String sql = "SELECT COUNT(*) FROM Users WHERE username = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);

            rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) == 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }
    public boolean authenticateUser(String username, String password) {
        boolean isAuthenticated = false;
        String sql = "SELECT * FROM Users WHERE username = ? AND password = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            rs = stmt.executeQuery();

            if (rs.next()) {
                isAuthenticated = true;
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isAuthenticated;
    }

    public User getUserByUsername(String username) {
        User user = null;
        String sql = "SELECT * FROM Users WHERE username = ?";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);

            rs = stmt.executeQuery();

            if (rs.next()) {
                user = new User(
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("role"),
                        rs.getString("created_at")
                );
                user.setUserId(rs.getString("user_id"));
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public List<Event> getAllEvents() {
        List<Event> eventList = new ArrayList<>();
        String sql = "SELECT * FROM Events";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Event event = new Event();
                event.setEventId(rs.getString("event_id"));
                event.setEventName(rs.getString("event_name"));
                event.setCategory(rs.getString("category"));
                event.setEventDate(rs.getDate("event_date"));
                event.setCapacity(rs.getInt("capacity"));
                event.setRegisteredCount(rs.getInt("registered_count"));
                event.setDescription(rs.getString("description"));
                event.setStatus(rs.getString("status"));
                eventList.add(event);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return eventList;
    }

    public List<Event> getAttendedEvents(String userId) {
        List<Event> attendedEvents = new ArrayList<>();
        String sql = "SELECT E.event_id, E.event_name, E.category, E.event_date, E.capacity, E.registered_count, E.description "
                + "FROM Events E "
                + "INNER JOIN Participants P ON E.event_id = P.event_id "
                + "WHERE P.user_id = ? AND E.event_date < CURRENT_DATE";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, userId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Event event = new Event();
                event.setEventId(rs.getString("event_id"));
                event.setEventName(rs.getString("event_name"));
                event.setCategory(rs.getString("category"));
                event.setEventDate(rs.getDate("event_date"));
                event.setDescription(rs.getString("description"));
                event.setCapacity(rs.getInt("capacity"));
                event.setRegisteredCount(rs.getInt("registered_count"));
                attendedEvents.add(event);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return attendedEvents;
    }
    public List<Event> getUserUpcomingEvents(String userId) {
        List<Event> attendedEvents = new ArrayList<>();
        String sql = "SELECT E.event_id, E.event_name, E.category, E.event_date, E.capacity, E.registered_count, E.description, P.registration_status "
                + "FROM Events E "
                + "INNER JOIN Participants P ON E.event_id = P.event_id "
                + "WHERE P.user_id = ? AND E.event_date > CURRENT_DATE";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, userId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Event event = new Event();
                event.setEventId(rs.getString("event_id"));
                event.setEventName(rs.getString("event_name"));
                event.setCategory(rs.getString("category"));
                event.setEventDate(rs.getDate("event_date"));
                event.setDescription(rs.getString("description"));
                event.setCapacity(rs.getInt("capacity"));
                event.setRegisteredCount(rs.getInt("registered_count"));
                event.setStatus(rs.getString("registration_status"));
                attendedEvents.add(event);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attendedEvents;
    }

    public boolean cancelRegistration(String userId, String eventId) {
        boolean canceled = false;
        String sql = "DELETE FROM Participants WHERE event_id = ? and user_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, eventId);
            stmt.setString(2, userId);
          int rs=  stmt.executeUpdate();

          if(rs > 0) {
              canceled = true;
              decreaseRegistrations(eventId,1);
          }
            conn.close();

        }catch (SQLException e ){
            e.printStackTrace();
        }
        return canceled;
    }
    public boolean unregisterOnCancelStatus(String eventId) {
        boolean canceled = false;
        String sql = "DELETE FROM Participants WHERE event_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, eventId);
            int rs=  stmt.executeUpdate();
            if(rs > 0) {

                canceled = true;
                System.out.println("here");
                decreaseRegistrations(eventId,0);
            }
            conn.close();

        }catch (SQLException e ){
            e.printStackTrace();
        }
        return canceled;
    }
    public void decreaseRegistrations(String eventId, int capacity) {
        String sql=" ";
        if(capacity>0){
            sql = "UPDATE Events SET registered_count = registered_count - ? WHERE event_id = ?";
        }else{
            sql="UPDATE Events SET registered_count = ? WHERE event_id = ?";
        }

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DatabaseUtil.getConnection();
            stmt=conn.prepareStatement(sql);
            stmt.setInt(1, capacity);
            stmt.setString(2, eventId);
            stmt.executeUpdate();
            conn.close();
        }catch (SQLException e ){
            e.printStackTrace();
        }
    }
    public boolean registrationStatusChanged(String p_id,String Status) {
        System.out.println(p_id);

        boolean statusChanged = false;
        String sql ="UPDATE Participants SET registration_status = ? WHERE participant_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,Status);
            stmt.setString(2, p_id);

           int rs = stmt.executeUpdate();
           if(rs > 0) {
               statusChanged = true;
           }
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return statusChanged;
    }

    public boolean submitFeedback(Feedback feedback) {

        String sql = "INSERT INTO Feedback (feedback_id, event_id, user_id, rating, comments) "
                + "VALUES (?, ?, ?, ?, ?)";

        try {
            Connection connection = DatabaseUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            String feedbackId = generateFeedbackId();

            statement.setString(1, feedbackId);
            statement.setString(2, feedback.getEventId());
            statement.setString(3, feedback.getUserId());
            statement.setInt(4, feedback.getRating());
            statement.setString(5, feedback.getComments());

            int rowsAffected = statement.executeUpdate();
            connection.close();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Feedback getFeedbackByUserAndEvent(String userId, String eventId) {
        Feedback feedback = null;
        String sql = "SELECT * FROM Feedback WHERE user_id = ? AND event_id = ?";

        try {
            Connection conn = DatabaseUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, userId);
            stmt.setString(2, eventId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                feedback = new Feedback();
                feedback.setFeedbackId(rs.getString("feedback_id"));
                feedback.setEventId(rs.getString("event_id"));
                feedback.setUserId(rs.getString("user_id"));
                feedback.setRating(rs.getInt("rating"));
                feedback.setComments(rs.getString("comments"));
                feedback.setSubmittedAt(rs.getString("submitted_at"));
            }

            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return feedback;
    }

    public List<Event> getFilteredEvents(String category, String eventDate, String keyword, boolean upcoming) {
        List<Event> events = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM Events WHERE 1=1");


        if (category != null && !category.isEmpty()) {
            sql.append(" AND category LIKE ?");
        }
        if (eventDate != null && !eventDate.isEmpty()) {
            sql.append(" AND event_date = ?");
        }
        if (keyword != null && !keyword.isEmpty()) {
            sql.append(" AND event_name LIKE ?");
        }
        if (upcoming) {
            sql.append(" AND event_date > CURRENT_DATE");
        }

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql.toString());

            int index = 1;

            if (category != null && !category.isEmpty()) {
                stmt.setString(index++, "%" + category + "%");
            }
            if (eventDate != null && !eventDate.isEmpty()) {
                stmt.setString(index++, eventDate);
            }
            if (keyword != null && !keyword.isEmpty()) {
                stmt.setString(index++, "%" + keyword + "%");
            }

            rs = stmt.executeQuery();

            while (rs.next()) {
                Event event = new Event();
                event.setEventId(rs.getString("event_id"));
                event.setEventName(rs.getString("event_name"));
                event.setCategory(rs.getString("category"));
                event.setEventDate(rs.getDate("event_date"));
                event.setCapacity(rs.getInt("capacity"));
                event.setRegisteredCount(rs.getInt("registered_count"));
                event.setDescription(rs.getString("description"));
                events.add(event);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }

    public boolean isUserAlreadyRegistered(String eventId, String userId) {
        String checkQuery = "SELECT COUNT(*) FROM Participants WHERE event_id = ? AND user_id = ?";
        Connection conn = null;
        PreparedStatement checkStmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            checkStmt = conn.prepareStatement(checkQuery);
            checkStmt.setString(1, eventId);
            checkStmt.setString(2, userId);
            rs = checkStmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean registerUserForEvent(String eventId, String userId) {
        String insertQuery = "INSERT INTO Participants (participant_id, event_id, user_id) VALUES (?, ?, ?)";
        Connection conn = null;
        PreparedStatement insertStmt = null;

        try {
            conn = DatabaseUtil.getConnection();
            insertStmt = conn.prepareStatement(insertQuery);
            String participantId = generateParticipantId();
            insertStmt.setString(1, participantId);
            insertStmt.setString(2, eventId);
            insertStmt.setString(3, userId);
            int rowsInserted = insertStmt.executeUpdate();
            increaseRegisteredCount(eventId);
            conn.close();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public void increaseRegisteredCount(String eventId) {

        String sql = "Update Events SET registered_count = registered_count + 1 WHERE event_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DatabaseUtil.getConnection();
            stmt=conn.prepareStatement(sql);
            stmt.setString(1, eventId);
            stmt.executeUpdate();
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        };

    }
    public boolean checkStatus(String eventId) {
        boolean canceled = false;
        String sql = "SELECT * FROM Events WHERE event_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt= conn.prepareStatement(sql);
            stmt.setString(1, eventId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                if (rs.getString("status").equals("Canceled")) {
                    canceled = true;
                }
            }
        conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return canceled;
    }
    public boolean datePassed(String eventId){
        boolean flag=false;
        String sql = "SELECT * FROM Events WHERE event_id = ? and event_date < CURRENT_DATE";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
       try {
           conn = DatabaseUtil.getConnection();
           stmt= conn.prepareStatement(sql);
           stmt.setString(1, eventId);
           rs = stmt.executeQuery();
           if (rs.next()) {
               flag = true;
           }
           conn.close();

       }catch (SQLException e){
           e.printStackTrace();
       }
        return flag;
    }

    public List<Participant> getParticipantsByEvent(String eventId) {
        List<Participant> participants = new ArrayList<>();
        String sql = "SELECT p.participant_id, p.event_id, u.username,p.registered_at,p.registration_status FROM Participants p JOIN Users u ON p.user_id=u.user_id WHERE p.event_id = ?";

        try {
            Connection conn = DatabaseUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, eventId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Participant participant = new Participant();
                    participant.setParticipantId(rs.getString("participant_id"));
                    participant.setEventId(rs.getString("event_id"));
                    participant.setUserId(rs.getString("username"));
                    participant.setRegisteredAt(rs.getTimestamp("registered_at"));
                    participant.setRegistrationStatus(rs.getString("registration_status"));
                    participants.add(participant);
                }
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return participants;
    }

    public boolean addEvent(String eventName, String category, String eventDate, int capacity, String description,String userId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DatabaseUtil.getConnection();
            String sql = "INSERT INTO Events (event_id, event_name, category, event_date, capacity, registered_count, description, created_by) " +
                    "VALUES (?, ?, ?, ?, ?, 0, ?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, generateEventId());
            stmt.setString(2, eventName);
            stmt.setString(3, category);
            stmt.setString(4, eventDate);
            stmt.setInt(5, capacity);
            stmt.setString(6, description);
            stmt.setString(7, userId);

            int rowsInserted = stmt.executeUpdate();
            conn.close();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateEvent(String eventId, String eventName, String category, java.util.Date eventDate, int capacity, String description,String status) {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "UPDATE Events SET event_name = ?, category = ?, event_date = ?, capacity = ?, description = ?, status = ? WHERE event_id = ?";

        try {
            conn = DatabaseUtil.getConnection();

            java.sql.Date sqlDate = new java.sql.Date(eventDate.getTime());

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, eventName);
            stmt.setString(2, category);
            stmt.setDate(3, sqlDate);
            stmt.setInt(4, capacity);
            stmt.setString(5, description);
            stmt.setString(6, status);
            stmt.setString(7, eventId);
            int rowsUpdated = stmt.executeUpdate();
            conn.close();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean deleteEvent(String eventId) {
        String sql = "DELETE FROM Events WHERE event_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, eventId);
            int rowsDeleted = stmt.executeUpdate();
            conn.close();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean cancelEvent(String eventId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean isCanceled = false;

        try {
            conn = DatabaseUtil.getConnection();
            String sql = "UPDATE Events SET status = 'Canceled' WHERE event_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, eventId);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                isCanceled = true;
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isCanceled;
    }
    public Event getEventById(String eventId) {
        Event event = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            conn = DatabaseUtil.getConnection();

            String sql = "SELECT * FROM Events WHERE event_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, eventId);
            rs = pstmt.executeQuery();
            if (rs.next()) {

                event = new Event();
                event.setEventId(rs.getString("event_id"));
                event.setEventName(rs.getString("event_name"));
                event.setDescription(rs.getString("description"));
                event.setCategory(rs.getString("category"));
                event.setEventDate(rs.getTimestamp("event_date"));
                event.setCapacity(rs.getInt("capacity"));
                event.setRegisteredCount(rs.getInt("registered_count"));
                event.setStatus(rs.getString("status"));
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return event;
    }
    public String generateUserId() {
        String lastUserId = null;
        String newUserId = null;

        String sql = "SELECT user_id FROM Users ORDER BY user_id DESC LIMIT 1";

        try {
            Connection conn = DatabaseUtil.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                lastUserId = rs.getString("user_id");
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (lastUserId != null) {
            String numericPart = lastUserId.substring(1);
            int number = Integer.parseInt(numericPart);
            number++;
            newUserId = "U" + String.format("%03d", number);
        } else {
            newUserId = "U001";
        }

        return newUserId;
    }

    public String generateParticipantId() {
        String lastParticipantId = null;
        String newParticipantId = null;

        String sql = "SELECT participant_id FROM Participants ORDER BY participant_id DESC LIMIT 1";

        try {
            Connection conn = DatabaseUtil.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                lastParticipantId = rs.getString("participant_id");
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (lastParticipantId != null) {
            String numericPart = lastParticipantId.substring(1); // Removing "P"
            int number = Integer.parseInt(numericPart);
            number++;
            newParticipantId = "P" + String.format("%03d", number); // Format to 3 digits
        } else {
            newParticipantId = "P001";
        }
        return newParticipantId;
    }

    public String generateFeedbackId() {
        String lastFeedbackId = null;
        String newFeedbackId = null;

        String sql = "SELECT feedback_id FROM Feedback ORDER BY feedback_id DESC LIMIT 1";

        try {
            Connection conn = DatabaseUtil.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                lastFeedbackId = rs.getString("feedback_id");
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (lastFeedbackId != null) {
            String numericPart = lastFeedbackId.substring(1); // Removing "F"
            int number = Integer.parseInt(numericPart);
            number++;
            newFeedbackId = "F" + String.format("%03d", number); // Format to 3 digits
        } else {
            newFeedbackId = "F001";
        }
        return newFeedbackId;
    }

    public String generateEventId() {
        String lastEventId = null;
        String newEventId = null;

        String sql = "SELECT event_id FROM Events ORDER BY event_id DESC LIMIT 1";

        try {
            Connection conn = DatabaseUtil.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                lastEventId = rs.getString("event_id");
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (lastEventId != null) {

            String numericPart = lastEventId.substring(1);
            int number = Integer.parseInt(numericPart);
            number++;
            newEventId = "E" + String.format("%03d", number); // Format to 3 digits
        } else {

            newEventId = "E001";
        }
        return newEventId;
    }
}