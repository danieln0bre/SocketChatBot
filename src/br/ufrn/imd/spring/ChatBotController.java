package br.ufrn.imd.spring;

import br.ufrn.imd.socket.ChatBot;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chatbot")
public class ChatBotController {

    @GetMapping()
    public String responseChatBotTest(){
        return "Hello";
    }
    @PostMapping()
    public String responseChatBot(@RequestBody String question){
        return ChatBot.chatBotResponse(question);
    }
}
