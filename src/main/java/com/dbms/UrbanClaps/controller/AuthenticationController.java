//package com.dbms.UrbanClaps.controller;
//
//import com.dbms.UrbanClaps.dao.UserDao;
//import com.dbms.UrbanClaps.model.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import javax.servlet.http.HttpSession;
//
//@Controller
//public class AuthenticationController {
//
//
////    @Autowired
////    private UserDao userDao;
////    private String SESSION_AUTH_KEY = "AUTH_USER";
////
////    public Boolean checkCredentials(String username, String password) {
////        User user = userDao.getUserByUsernameAKAEmailId(username);
////        return user.getPassword().equals(password);
////    }
////
////    public void loginUser(HttpSession session, String username) {
////        session.setAttribute(SESSION_AUTH_KEY, username);
////    }
////
////    public void logoutUser(HttpSession session) {
////        session.removeAttribute(SESSION_AUTH_KEY);
////    }
////
////    public String getCurrentUser(HttpSession session) {
////        try {
////            return session.getAttribute(SESSION_AUTH_KEY).toString();
////        } catch (Exception e) {
////            return null;
////        }
////    }
////
////
////    public Boolean isAuthenticated(HttpSession session) {
////        return getCurrentUser(session) != null;
////    }
////
////    @GetMapping("/login")
////    public String login(HttpSession session) {
////        if (isAuthenticated(session)) {
////            return "redirect:/";
////        }
////
////
////        return "dashboard/login";
////    }
////
////
////
//
//}
