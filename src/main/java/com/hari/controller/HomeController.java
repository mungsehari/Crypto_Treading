package com.hari.controller;

import com.hari.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping
    public String home(){
        return "Welcome to  treading platform !";
    }
    public ResponseEntity<ApiResponse> HomeController(){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Welcome to treading platform !");
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
