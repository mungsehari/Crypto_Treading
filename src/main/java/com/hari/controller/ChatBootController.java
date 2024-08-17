package com.hari.controller;


import com.hari.dto.PromptBody;
import com.hari.response.ApiResponse;
import com.hari.service.ChatBootService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ai/chat")
public class ChatBootController {
    private final ChatBootService chatBootService;

    public ChatBootController(ChatBootService chatBootService) {
        this.chatBootService = chatBootService;
    }


    @PostMapping
    public ResponseEntity<ApiResponse> getCoinDetails(@RequestBody PromptBody prompt) throws Exception {

        ApiResponse response =chatBootService.getCoinDetails(prompt.getPrompt());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/simple")
    public ResponseEntity<String > simpleChatHandler(@RequestBody PromptBody prompt) throws Exception {
     String response= chatBootService.simpleChat(prompt.getPrompt());
//        ApiResponse response=new ApiResponse();
//        response.setMessage(prompt.getPrompt());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
