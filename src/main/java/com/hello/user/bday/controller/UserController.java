package com.hello.user.bday.controller;

import com.hello.user.bday.RestResponse;
import com.hello.user.bday.model.User;
import com.hello.user.bday.repository.UserRepository;
import com.hello.user.bday.util.Util;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@RestController
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/hello/{userName}")
    RestResponse<String> getHelloMessage(@PathVariable("userName") String userName) {
        String message;
        User user = userRepository.findByName(userName);
        Date date = user.getDob();
        String name = user.getName();
        date.setYear((new Date()).getYear());
        Date today = new Date();
        long duration = today.getTime() - date.getTime();
        long diffInDays = TimeUnit.MILLISECONDS.toDays(duration);
        if (diffInDays < 0) {
            diffInDays = -diffInDays;
            message = "Hello, " + name + "! Your birthday is in " + diffInDays + " day(s)";
        } else if (diffInDays > 0) {
            diffInDays += 365;
            message = "Hello, " + name + "! Your birthday is in " + diffInDays + " day(s)";
        } else {
            message = "Hello, " + name + "! Happy birthday! ";
        }
        return new RestResponse<>(message, 200);
    }

    @PutMapping("/hello/{userName}/{date}")
    RestResponse<String> updateDateOfBirth(@PathVariable("userName") String userName, @PathVariable("date") String date) throws ParseException {
        if (Util.isStringOnlyAlphabet(userName) && Util.validateJavaDate(date)) {
            userRepository.save(new User(userName, new SimpleDateFormat("yyyy-MM-dd").parse(date)));
            return new RestResponse<>("No Content", 204);
        } else {
            return new RestResponse<>("Format entered is not Valid", 204);
        }
    }
}
