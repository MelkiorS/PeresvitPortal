package ua.peresvit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.peresvit.entity.Message;
import ua.peresvit.entity.User;
import ua.peresvit.service.MessageService;
import ua.peresvit.service.UserService;

import java.util.LinkedList;

@Controller
@RequestMapping(value = "/admin/user")
public class MessageController {
    @Autowired
    MessageService messageService;
    @Autowired
    UserService userService;

    @RequestMapping(value = "send")
    public void sendMessage(Message message, @PathVariable("userId")  long userId, Model model) {
        messageService.sendMessage(userService.getCurrentUser(), userService.findOne(userId), message);
    }

    @RequestMapping(value = "inbox")
    public String getInbox(Model model) {
        model.addAttribute(userService.getCurrentUser().getReceivedMessages());
        return "message/inbox";
    }

    @RequestMapping(value = "sent")
    public String getSent(Model model) {
        model.addAttribute(userService.getCurrentUser().getSentMessages());
        return "message/sent";
    }

    @RequestMapping(value = "dialog")
    public String getSent(long userId, Model model) {
        User current = userService.getCurrentUser();
        User dialogUser = userService.findOne(userId);
        LinkedList<Message> dialog = new LinkedList<>();
        // need to optimize
        dialog = messageService.findAllByAuthorAndReceiver(current, dialogUser);
        dialog.addAll(messageService.findAllByAuthorAndReceiver(dialogUser, current));
        // sort by create at
        dialog.stream().sorted((m1 , m2 )-> (int)(m1.getCreatedAt() - m2.getCreatedAt()));
        model.addAttribute("dialog", dialog);
        return "message/dialog";
    }
}
