package ua.peresvit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.peresvit.entity.Chat;
import ua.peresvit.entity.Message;
import ua.peresvit.service.MessageService;
import ua.peresvit.service.UserService;

import java.util.List;

@Controller
@RequestMapping(value = "/home/messages")
public class MessageController {
    final MessageService messageService;
    final UserService userService;

    @Autowired
    public MessageController(MessageService messageService, UserService userService) {
        this.messageService = messageService;
        this.userService = userService;
    }
//  after entering main messages' page we get list of all chats, we can create new chat or send new message
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getAllChats(Model model) {
        List<Chat> chats = messageService.findAllUsersChats(userService.getCurrentUser());
        model.addAttribute("chatList", chats);
        model.addAttribute("newMessage", new Message());
        model.addAttribute("newChat", new Chat());
        return "home/chats";
    }
//  creating new chat from main messages' page
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String sentMessageFromMainPage(Chat newChat, Model model) {
        if (!newChat.getChatTitle().equals("") && !newChat.getMembers().isEmpty()) {
            newChat = messageService.saveChat(newChat);
            model.addAttribute("chatId", newChat.getChatId());
            return "redirect:/home/messages/{chatId}";
        } else {
//            model.addAttribute("message", )
            return "home/chats";
        }
    }
//  here we enter certain chat with it's messages
    @RequestMapping(value = "/{chatId}", method = RequestMethod.GET)
    public String getSingleChat(@PathVariable("chatId") Long chatId, Model model) {
        Chat currentChat = messageService.findOneChat(chatId);
        model.addAttribute("chat", currentChat);
        model.addAttribute("messagesList", messageService.findChatOrderByCreatedAt(chatId));
        model.addAttribute("newMessage", new Message());
        return "home/messages";
    }
// post new message to certain chat and refresh chat
    @RequestMapping(value = "/{chatId}", method = RequestMethod.POST)
    public String sendMessageFromSingleChat(@PathVariable("chatId") Long chatId, Model model, Message newMessage) {
        messageService.sendMessage(userService.getCurrentUser(), newMessage);
        model.addAttribute("chatId", chatId);
        return "redirect:/home/messages/{chatId}";
    }
}
