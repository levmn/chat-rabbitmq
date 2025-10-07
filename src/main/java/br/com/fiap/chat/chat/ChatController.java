package br.com.fiap.chat.chat;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/chat")
public class ChatController {

    private final ChatMessageSender chatMessageSender;
    private final ChatMessageReceiver chatMessageReceiver;

    public ChatController(ChatMessageSender chatMessageSender, ChatMessageReceiver chatMessageReceiver) {
        this.chatMessageSender = chatMessageSender;
        this.chatMessageReceiver = chatMessageReceiver;
    }

    @PostMapping("/send")
    public String sendMessage(String username, String message,String gender, RedirectAttributes redirect) {
        String formattedMessage = username + ": " + message;
        chatMessageSender.sendMessage(formattedMessage);
        redirect.addFlashAttribute("username", username);
        redirect.addFlashAttribute("gender", gender);
        return "redirect:/chat";
    }
}
