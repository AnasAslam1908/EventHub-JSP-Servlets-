# EventHub-JSP-Servlets
## Introduction

EventHub is a web-based platform designed to facilitate the smooth organization and participation in various events such as conferences, workshops, and webinars. It provides an easy-to-use interface for administrators to manage events and offers users a seamless experience to browse, register, and provide feedback on events. With real-time updates and advanced analytics, EventHub serves as a comprehensive solution for event management.

## Features

### User Functionalities

1. **User Login and Registration**
   - Users can create accounts and log in for a personalized experience.
   - Role-based access ensures users can only perform authorized actions.

2. **Browse and Search Events**
   - Users can view and filter events by category, date, or keywords.

3. **Register for Events**
   - Users can register for events they are interested in.

4. **Provide Feedback**
   - After attending events, users can submit ratings and comments.

5. **View Dashboard**
   - Users can view a dashboard containing details of both upcoming and previously attended events.

### Admin Functionalities

1. **Admin Login**
   - Admins have role-specific access, allowing them to manage events and participants.

2. **Manage Events**
   - Admins can add, update, cancel, or delete events. They can also update event availability in real-time.

3. **View and Manage Participants**
   - Admins can monitor participant registrations and manage waitlists for events.

## Technologies Used

### Front-End Technologies
- **JSP (JavaServer Pages):** Dynamic web pages for event registration, user dashboards, and feedback forms.
- **HTML:** Structures the content of the web pages.
- **CSS & Bootstrap:** Enhances the design, layout, and responsiveness of the platform.

### Back-End Technologies
- **Java Servlets:** Handles the business logic, processes form submissions, and interacts with the database for dynamic data.
- **MySQL Database:** Stores all system data including user details, event information, participant registrations, and feedback.

## Database Schema

### Key Tables:
1. **Users:** Stores user details and roles (Admin, User).
2. **Events:** Stores event details such as event name, date, time, location, and capacity.
3. **Participants:** Tracks which users have registered for specific events.
4. **Feedback:** Stores user ratings and comments for events they attended.

## Installation

### Prerequisites
- Java 8 or higher
- Apache Tomcat (for running the JSP/Servlet application)
- MySQL Database

### Steps to Set Up
1. **Clone the repository** to your local machine

2. **Set up the MySQL database:**
- Create a new database called `scdproject`.
- Import the database schema (provided in the `Schema.sql` file) to create the necessary tables.

3. **Configure the database connection:**
- Update the database connection details (hostname, username, password) in the `DatabaseUtil.java`

4. **Deploy the project on Apache Tomcat:**
- Build and deploy the project in your Apache Tomcat server.
- Start the server and navigate to `http://localhost:8085/EventHub`.
