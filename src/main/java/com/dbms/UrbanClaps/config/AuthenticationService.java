package com.dbms.UrbanClaps.config;


import com.dbms.UrbanClaps.dao.UserDao;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    UserDao userDao;
    private String USER_ATTRIBUTE = "AUTH_USER";
    private String USER_ROLE = "AUTH_ROLE";

    /*public Boolean checkCredentials(String username, String password) {
        User user = userDao.getUser(username);
        return user.getPassword().equals(password);
    }*/
    /*DONE IN Login Controller*/

    public void loginUser(HttpSession session, Long userId, Long role) {
        session.setAttribute(USER_ATTRIBUTE, userId);
        System.out.println(session.getAttribute(USER_ATTRIBUTE));
        session.setAttribute(USER_ROLE,role);
        System.out.println(session.getAttribute(USER_ROLE));
    }

    public void logoutUser(HttpSession session) {
        session.removeAttribute(USER_ATTRIBUTE);
        session.removeAttribute(USER_ROLE);
    }


    public String getCurrentUser(HttpSession session) {
        try {
            return session.getAttribute(USER_ATTRIBUTE).toString();
        } catch (Exception e) {
            return null;
        }
    }

    public String getCurrentRole(HttpSession session) {
        try {
            return  session.getAttribute(USER_ROLE).toString();
        } catch (Exception e) {
            return null;
        }
    }


    public Boolean isAuthenticated(HttpSession session) {
        return getCurrentUser(session) != null;
    }


}