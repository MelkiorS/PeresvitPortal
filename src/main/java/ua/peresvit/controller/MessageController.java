package ua.peresvit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.peresvit.entity.Chat;
import ua.peresvit.entity.Message;
import ua.peresvit.entity.User;
import ua.peresvit.entity.UserGroup;
import ua.peresvit.service.MessageService;
import ua.peresvit.service.UserService;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping(value = "/home/messages")
public class MessageController {
    final MessageService messageService;
    final UserService userService;
    final MessageSource messages;

    @Autowired
    public MessageController(MessageService messageService, UserService userService, MessageSource messages) {
        this.messageService = messageService;
        this.userService = userService;
        this.messages = messages;
    }

//  after entering main messages' page we get list of all chats, we can create new chat or send new message
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getAllChats(Model model) {
        List<Chat> chats = messageService.findUserChats(userService.getCurrentUser());
        model.addAttribute("chatList", chats);
        model.addAttribute("newMessage", new Message());
        model.addAttribute("newChat", new Chat());
        return "home/chats";
    }

//  creating new chat from main messages' page
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String createNewChatFromMainPage(Chat newChat, Model model, Locale locale) {
        if (!newChat.getChatTitle().equals("") && !newChat.getMembers().isEmpty()) {
            User creator = userService.getCurrentUser();
            newChat.setOwner(creator);
            newChat = messageService.saveChat(newChat);
//            Adding message about chat creation
            messageService.sendMessage(creator, messageService.getNewChatCreatingMessage(creator, newChat, locale));
            model.addAttribute("chatId", newChat.getChatId());
            return "redirect:/home/messages/{chatId}";
        } else {
            model.addAttribute("message", messages.getMessage("message.chatCreationError", null, locale));
            return "home/chats";
        }
    }

//  here we enter certain chat with it's messages
    @RequestMapping(value = "/{chatId}", method = RequestMethod.GET)
    public String getSingleChat(@PathVariable("chatId") Long chatId, Model model) {
        Chat currentChat = messageService.findOneChat(chatId);
//      adding owner permissions to the chat view
        if (userService.getCurrentUser().equals(currentChat.getOwner())) {
            model.addAttribute("ownerPermission", true);
        }
        model.addAttribute("chat", currentChat);
        model.addAttribute("messagesList", messageService.findChatOrderByCreatedAt(chatId));
        model.addAttribute("newMessage", new Message());
        return "home/messages";
    }

// post new message to certain chat and refresh chat
    @RequestMapping(value = "/{chatId}", method = RequestMethod.POST)
    public String sendMessageFromChat(@PathVariable("chatId") Long chatId, Model model, Message newMessage) {
        messageService.sendMessage(userService.getCurrentUser(), newMessage);
        model.addAttribute("chatId", chatId);
        return "redirect:/home/messages/{chatId}";
    }

//  getting the list of members to add to the certain chat
    @RequestMapping(value = "/{chatId}/addMembers", method = RequestMethod.GET)
    public String getListOfNewMembersToChat(@PathVariable("chatId") Long chatId, Model model) {
        Chat currentChat = messageService.findOneChat(chatId);
        List<User> chatMembers = (List<User>) currentChat.getMembers();
        User currentUser = userService.getCurrentUser();
        List<UserGroup> ug = userService.getUserGroups(currentUser);
        LinkedList<User> membersToAdd = new LinkedList<>();
        for (User u : userService.getGroupsUsers((UserGroup[]) ug.toArray())) {
            if (!chatMembers.contains(u)) {
                membersToAdd.add(u);
            }
        }
        model.addAttribute("membersToAdd", membersToAdd);
        return "home/addMembersToChat";
    }

//  adding new members to certain chat
    @RequestMapping(value = "/{chatId}/addMembers", method = RequestMethod.POST)
    public String addNewMembersToChat(@PathVariable("chatId") Long chatId,
                                      Model model,
                                      Locale locale,
                                      LinkedList<User> membersToAdd,
                                      Chat currentChat) {
        if (!membersToAdd.isEmpty()) {
            User inviter = userService.getCurrentUser();
            messageService.addNewMembersToChat(membersToAdd, currentChat);
            messageService.sendMessage(inviter, messageService.getAddingNewMemberMessage(inviter, membersToAdd, currentChat, locale));
            model.addAttribute("chatId", chatId);
            return "redirect:/home/messages/{chatId}";
        } else {
            model.addAttribute("message", messages.getMessage("message.addingNewMemberToChatError", null, locale));
            return "home/chats";
        }
    }

//   get form to post message to one user
    @RequestMapping(value = "/postMessage/{userId}", method = RequestMethod.GET)
    public String getFormForNewMessage(@PathVariable("userId") Long userId, Model model) {
        Chat currentChat = messageService.findDialog(Arrays.asList(userService.getCurrentUser(), userService.findOne(userId)));
        model.addAttribute("newMessage", new Message());
        model.addAttribute("chat", currentChat);
        return "home/newMessage";
    }

//    post message to user
    @RequestMapping(value = "/postMessage/{userId}", method = RequestMethod.POST)
    public  String postNewMessage(@PathVariable("userId") Long userId, Message newMessage, Model model, Locale locale) {
        if (! newMessage.getContent().isEmpty()) {
            messageService.sendMessage(userService.getCurrentUser(), newMessage);
            return "redirect:/home/messages";
        } else {
//            TODO create new message
            model.addAttribute("message", messages.getMessage("", null, locale));
            return "home/newMessage";
        }
    }
}
