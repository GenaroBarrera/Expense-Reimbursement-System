package com.revature.servlets;

import com.revature.user.User;
import com.revature.user.UserDaoImpl;
import com.revature.util.SessionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(RegisterServlet.class.getName()); //set up our logger

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {


        req.getRequestDispatcher("Register.html").forward(req, res);
    }
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String fname = req.getParameter("fname");
        String lname = req.getParameter("lname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User user = new User(false, fname, lname, email, password);
        UserDaoImpl impl = new UserDaoImpl();
        impl.addUser(user);
        SessionUtil.logger.info("Employee Registered");
        req.getRequestDispatcher("ManagerPage.html").forward(req, res);
    }
}
