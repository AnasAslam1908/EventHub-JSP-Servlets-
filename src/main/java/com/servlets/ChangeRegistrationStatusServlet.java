package com.servlets;
import com.classes.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
@WebServlet(urlPatterns = {"/approveRegistration", "/cancelRegistration","/pendingRegistration"})
public class ChangeRegistrationStatusServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String p_id=req.getParameter("p_Id");
        String action=req.getParameter("action");
        UserService us=new UserService();
       boolean changed = false;
       if(action.equals("approve")) {
           System.out.println("approve");
           changed    = us.registrationStatusChanged(p_id,"Approved");
       }else if(action.equals("cancel")) {
           System.out.println("cancel");
           changed    = us.registrationStatusChanged(p_id,"Cancelled");
       }else if(action.equals("pending")) {
           changed    = us.registrationStatusChanged(p_id,"Pending");
       }

       if(changed) {
           req.setAttribute("message", "Registration status changed.");
           req.getRequestDispatcher("viewParticipants.jsp").forward(req, resp);
       }else {
           req.setAttribute("message", "Error changing registration status.");
           req.getRequestDispatcher("viewParticipants.jsp").forward(req, resp);
       }
    }
}
