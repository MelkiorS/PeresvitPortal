package ua.peresvit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.peresvit.dto.ChatWithLastMessage;
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
        Set<ChatWithLastMessage> chats = messageService.findCustomChatsOfUser(userService.getCurrentUser());
        model.addAttribute("chatList", chats);
//        adding chat object to create new chat from start chats page
        model.addAttribute(new Chat());
        return "home/chats";
    }



    //  creating new chat from main messages' page
    @RequestMapping(value = "/newChat", method = RequestMethod.POST)
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
        User currentUser = userService.getCurrentUser();
        List<Message> messages = messageService.findMessagesByChatOrderByCreatedAt(chatId);
        Message lastMessage = messages.get(messages.size()-1);
        if (!lastMessage.isReadStatus() && !currentUser.equals(lastMessage.getSender())){
            lastMessage.setReadStatus(true);
            messageService.saveMessage(lastMessage);
        }
        if (!chat.getMembers().contains(currentUser)) {
            return "redirect:/home/messages";
        }
        if (chat.getChatTitle().equals("")) {
            for (User u : chat.getMembers()) {
                if (!u.equals(currentUser)) {
                    chat.setChatTitle(u.getFirstName() + " " + u.getLastName());
                }
            }
        }
//      adding owner permissions to the chat view
        if ((currentUser.equals(chat.getOwner()) && chat.getMembers().size() != 2) || (currentUser.getRole().getRoleName().equals("ADMIN"))) {
            model.addAttribute("ownerPermission", true);
        }
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("ownerPermission", false);
        model.addAttribute(chat);
        model.addAttribute("messagesList", messages);
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
        model.addAttribute("status", "add");
        return "home/editMembersToChat";
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

    //    delete members
    @RequestMapping(value = "/{chatId}/deleteMembers", method = RequestMethod.GET)
    public String deleteMembersFromChat(@PathVariable("chatId") Long chatId, Model model) {
        Chat chat = messageService.findOneChat(chatId);
        User currentUser = userService.getCurrentUser();
        if (chat.getOwner().equals(currentUser) || currentUser.getRole().getRoleName().equals("ADMIN")) {
            model.addAttribute("chat", chat);
            model.addAttribute("charId", chatId);
            model.addAttribute("status", "delete");
            return "home/editMembersToChat";
        }
        model.addAttribute("message", "permission denied");
        return "redirect:/home/messages";
    }

//    delete chat
    @RequestMapping(value = "/{chatId}/deleteChat", method = RequestMethod.DELETE)
    public String deleteChat(@PathVariable("chatId") Long chatId, Model model) {
        Chat chat = messageService.findOneChat(chatId);
        User currentUser = userService.getCurrentUser();
        if (chat.getOwner().equals(currentUser) || currentUser.getRole().getRoleName().equals("ADMIN")) {
            messageService.deleteChat(chatId);
            return "home/editMembersToChat";
        }
        model.addAttribute("message", "permission denied");
        return "redirect:/home/messages";
    }

    //  change chat title
    @RequestMapping(value = "/{chatId}/changeTitle", method = RequestMethod.POST)
    public String changeTitleOfChat(@PathVariable("chatId") Long chatId, Chat chat, Model model) {
        if (!chat.getChatTitle().equals("")) {
            messageService.saveChat(chat);
        }
        model.addAttribute("chatId", chatId);
        return "redirect:/home/messages/{chatId}";
    }

    //    leave chat
    @RequestMapping(value = "/{chatId}/leaveChat", method = RequestMethod.POST)
    public String leaveChat(@PathVariable("chatId") Long chatId) {
        messageService.deleteMemberFromChat(userService.getCurrentUser(), messageService.findOneChat(chatId));
        return "redirect:/home/messages";
    }

    //   get to the chat to post message to one user
    @RequestMapping(value = "/postMessage/{userId}", method = RequestMethod.GET)
    public String getFormForNewMessage(@PathVariable("userId") Long userId, Model model) {
//        переделать метод поиска диалога на метод с двумя лонгами в аргументами
        User userOfDialog = userService.findOne(userId);
        Chat currentChat = messageService.findDialog(userOfDialog);
        if (currentChat == null) {
            currentChat = messageService.saveDialog(new User[]{userService.getCurrentUser(), userOfDialog});
        }
        model.addAttribute("chatId", currentChat.getChatId());
        return "redirect:/home/messages/{chatId}";
    }
}
