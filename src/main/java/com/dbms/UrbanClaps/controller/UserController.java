package com.dbms.UrbanClaps.controller;

import ch.qos.logback.core.joran.sanity.Pair;
import com.dbms.UrbanClaps.config.AuthenticationService;
import com.dbms.UrbanClaps.config.SecurityConfig;
import com.dbms.UrbanClaps.dao.*;
import com.dbms.UrbanClaps.model.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(path = "user")
@CrossOrigin(origins = "http://localhost:3000/")
public class UserController {

    @Autowired
    UserDao userDao;

    @Autowired
    ServicesProvidedDao servicesProvidedDao;

    @Autowired
    SlotDoa slotDoa;

    @Autowired
    OrderDao orderDao;
    @Autowired
    ServiceProviderDao serviceProviderDao;

    @Autowired
    SecurityConfig securityConfig;

    @Autowired
    ManagerDao managerDao;

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("createSlot")
    public @ResponseBody ResponseEntity<String> createSlot(@RequestParam String date, @RequestParam String time, @RequestParam Long serviceid) {

//        USER Sign IN REQD
        try {
            Long userid = (long) 5;

            List<Long> cur = slotDoa.findFreeProvidersGivenSlot(date, time, serviceid);
            if (cur == null) {
                return new ResponseEntity<>("No Provider Avaliable at this slot\n" +
                        "Please Select different Slot"
                        , HttpStatus.NO_CONTENT);
            } else {
                Slot slot = Slot.builder()
                        .user(userid)
                        .id(0L)
                        .time(time)
                        .date(date)
                        .provider(cur.get(0))
                        .service(serviceid)
                        .build();
                Long id = slotDoa.createNewSlot(slot);
                slot.setId(id);
                int a = orderDao.createOrder(slot);
                if (a == 0) {
                    return new ResponseEntity<>("Order cannot be created", HttpStatus.INTERNAL_SERVER_ERROR);
                }
                return new ResponseEntity<>("Slot and Order Created", HttpStatus.OK);
            }
        } catch (Exception e) {
            System.out.println(e.toString());

            e
                    .getCause()
                    .printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("{orderId}/done")
    public ResponseEntity<String> updateOrderStatus(@PathVariable Long orderId) {

        //        SERVICE PROVIDER Sign IN REQD
        try {
            if (orderId == null)
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

            List<Orders> check = orderDao.getOrderByOrderId(orderId);

            if (check == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                int obj = orderDao.updateOrderStatus(orderId);
//                System.out.println(obj.toString());
                return new ResponseEntity<>("Updated Order Id - " + obj, HttpStatus.OK);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("login")
    public ResponseEntity<String> checkAndGetIn(@RequestBody LoginUser loginUser, HttpSession session) {

        if(authenticationService.isAuthenticated(session)){
            return new ResponseEntity<>("ALREADY LOGGED IN",HttpStatus.OK);
        }
        try {
            List<LoginUser> result;
            assert loginUser.getRole() != null;
            if (loginUser.getRole().equals(1L)) {
//            CHECK IN WEBSITE_USER
                result = userDao.getWUCredentials(loginUser);

            } else if (loginUser.getRole().equals(2L)) {
//            CHECK IN SERVICE_PROVIDER
                result = serviceProviderDao.getProviderCredentials(loginUser);

            } else if (loginUser.getRole().equals(3L)) {
//            CHECK IN MANAGER TABLE
                result = managerDao.getManagerCredentials(loginUser);

            } else {
//            CHECK IN ADMIN TABLE
                result = userDao.getAdminCredentials(loginUser);

            }
            if (result.isEmpty()) {
                return new ResponseEntity<>("USER NOT FOUND", HttpStatus.NOT_FOUND);
            } else {
                String toCheckAgainst = result.get(0).getPassword();
                String given = loginUser.getPassword();
                System.out.println(toCheckAgainst +" v/s "+ result.get(0).getPassword());
                if (securityConfig.passwordEncoder().matches(given, toCheckAgainst)) {
                    User myUser = userDao.getUserByUsernameAKAEmailId(loginUser.getUsername());
                    authenticationService.loginUser(session, myUser.getUserId(), loginUser.getRole());
                    return new ResponseEntity<>("Welcome Back", HttpStatus.OK);
                } else {

                    return new ResponseEntity<>("WRONG PASSWORD", HttpStatus.EXPECTATION_FAILED);
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @GetMapping("session1")
    public Object sessionInfo(HttpSession session){
        Object longLongPair = authenticationService.getCurrentUser(session);
        return longLongPair;
    }



    @GetMapping("session2")
    public Object sessionInfo2(HttpSession session){
        Object longLongPair = authenticationService.getCurrentRole(session);
        return longLongPair;
    }
    @PostMapping("logout")
    public ResponseEntity<String> logoutBhai(HttpSession session){

        if(!authenticationService.isAuthenticated(session)){
            return new ResponseEntity<>("Can't Logout Because Not Logged In",HttpStatus.OK);
        }else {
            authenticationService.logoutUser(session);
            return new ResponseEntity<>("Succesfully Signed Out",HttpStatus.OK);
        }

    }


}




/*
    public int addCustomer(User user){
        String sql = "INSERT INTO User(username,password,firstName,lastName,contact,age,address,dateOfBirth,authority) VALUES(?,?,?,?,?,?,?,?,?)";
        KeyHolder keyholder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFirstName());
            ps.setString(4,user.getLastName());
            ps.setString(5,user.getContact());
            ps.setInt(6,user.getAge());
            ps.setString(7,user.getAddress());
            ps.setDate(8,user.getDateOfBirth());
            ps.setString(9, "C");
            return ps;
        }, keyholder);
*/
