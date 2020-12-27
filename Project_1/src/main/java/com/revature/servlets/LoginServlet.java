package com.revature.servlets;

import com.revature.user.User;
import com.revature.user.UserDaoImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login") //mapping for servlets
public class LoginServlet extends HttpServlet {
    private final Logger logger = LogManager.getLogger(LoginServlet.class.getName());

    UserDaoImpl impl = null;
    User user = UserDaoImpl.user;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        //Dispatch to the login page
        req.getRequestDispatcher("Login.html").forward(req, res);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        String email = req.getParameter("email"); //users will login with their email!!!
        String password = req.getParameter("password"); //these are the credentials a user will submit

        user = null;
        impl = new UserDaoImpl();

        logger.debug("can we get the user? " + impl.getUserByEmail(email)); //no the user is null, why?
        logger.debug("can we get the user by Id? " + impl.getUserById(1)); //no the user is null, why?

        user = impl.getUserByEmail(email); //get user by email TODO will this break if the email is invalid???

        logger.debug("can we get the user from user? " + user);

        //TODO what does this do?
        req.setAttribute("user", user); //To pass the value from servlet to html/jsp files, setAttribute() method is called by the request object.
        // setAttribute() method takes an input as an object which sends the data from servlet to the requesting website
        System.out.println("what is the user's password? " + user.getPassword());
        //authenticate the credentials with the ones in the database
        if(user.getPassword().equals(password)){ //if the password matches with user's password (the user returned by getUserByEmail)
            HttpSession session = req.getSession(); //open an http session (not a hibernate session)
            session.setAttribute("user",user);

            if(user.getIsManager()){ //if the user is a manager
                res.sendRedirect(req.getContextPath() + "/managerPage"); //redirect them to the managers page

            }else{
                res.sendRedirect(req.getContextPath() + "/employeePage"); //redirect them to the employee page
            }
        }else{
            req.getRequestDispatcher("LoginFailed.html").forward(req, res); //login failed, redirect them to a login fail paga
        }
    }
}
