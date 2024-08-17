package com.hari.service;

import com.hari.response.ApiResponse;

public interface ChatBootService {
    ApiResponse getCoinDetails(String prompt) throws Exception;
    String simpleChat(String prompt);

}
