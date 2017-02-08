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

import java.util.*;

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
        Set<Chat> chats = messageService.findUserChats(userService.getCurrentUser());
        model.addAttribute("chatList", chats);
//        adding chat object to create new chat from start chats page
        model.addAttribute(new Chat());
        return "home/chats";
    }

    //  creating new chat from main messages' page
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String createNewChatFromMainPage(Chat chat, Model model, Locale locale) {
        if (!Objects.equals(chat.getChatTitle(), "") && !chat.getMembers().isEmpty()) {
            chat = messageService.createNewChat(chat, locale);
            model.addAttribute("chatId", chat.getChatId());
            return "redirect:/home/messages/{chatId}";
        } else {
            model.addAttribute("message", messages.getMessage("message.chatCreationError", null, locale));
            return "home/chats";
        }
    }

    //  here we enter certain chat with it's messages
    @RequestMapping(value = "/{chatId}", method = RequestMethod.GET)
    public String getSingleChat(@PathVariable("chatId") Long chatId, Model model) {
        Chat chat = messageService.findOneChat(chatId);
//      adding owner permissions to the chat view
        if (userService.getCurrentUser().equals(chat.getOwner())) {
            model.addAttribute("ownerPermission", true);
        }
        model.addAttribute("ownerPermission", false);
        model.addAttribute("currentChat", chat);
        model.addAttribute("messagesList", messageService.findMessagesByChatOrderByCreatedAt(chatId));
        model.addAttribute(new Message());
        return "home/messages";
    }

    // post new message to certain chat and refresh chat
    @RequestMapping(value = "/{chatId}", method = RequestMethod.POST)
    public String sendMessageFromChat(@PathVariable("chatId") Long chatId, Model model, Message message) {
        messageService.sendMessage(userService.getCurrentUser(), message);
        model.addAttribute("chatId", chatId);
        return "redirect:/home/messages/{chatId}";
    }

    //  getting the list of members to add to the certain chat
    @RequestMapping(value = "/{chatId}/addMembers", method = RequestMethod.GET)
    public String getListOfNewMembersToChat(@PathVariable("chatId") Long chatId, Model model) {
        Chat currentChat = messageService.findOneChat(chatId);
        Set<User> chatMembers = currentChat.getMembers();
        User currentUser = userService.getCurrentUser();
        List<UserGroup> ug = userService.getUserGroups(currentUser);
        List<User> membersToAdd = new LinkedList<>();
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
        Set<User> members = new HashSet<>();
        members.add(userService.getCurrentUser());
        members.add(userService.findOne(userId));
//        переделать метод поиска диалога на метод с двумя лонгами в аргументами
        Chat currentChat = messageService.findDialog(userService.getCurrentUser());
        if (currentChat == null) {
            currentChat = messageService.saveDialog(new User[]{userService.getCurrentUser(), userService.findOne(userId)});
            System.out.println(currentChat.getChatId());
        }
        model.addAttribute("chatId", currentChat.getChatId());
        return "redirect:/home/messages/{chatId}";
    }
}
