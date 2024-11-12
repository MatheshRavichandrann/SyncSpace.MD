package com.mugiwara.SyncSpace.MD.chat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ChatController {
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public") // it'll send that message to this end point
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage){ // each time it receive a message. look at line 12
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public") // it'll send that message to this end point
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor){ // it'll allow user to establish a connection user and the webSocket
//        Add username in webSocket Session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }
}
