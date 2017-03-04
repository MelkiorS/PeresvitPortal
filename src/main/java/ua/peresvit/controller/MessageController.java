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
        User currentUser = userService.getCurrentUser();
        Set<ChatWithLastMessage> chats = messageService.findCustomChatsOfUser(currentUser);
        for (ChatWithLastMessage chat : chats) {
            if (chat.getChatTitle().equals("dialog")) {
                for (User u : messageService.findOneChat(chat.getChatId()).getMembers()) {
                    if (!u.equals(currentUser)) {
                        chat.setChatTitle(u.getFirstName() + " " + u.getLastName());
                    }
                }
            }
        }
        model.addAttribute("chatList", chats);
//        adding chat object to create new chat from start chats page
        model.addAttribute(new Chat());
        return "home/chats";
    }

    //  creating new chat from main messages' page
    @RequestMapping(value = "/newChat", method = RequestMethod.GET)
    public String createFormForNewChat(Model model) {
        model.addAttribute(new Chat());
        List<UserGroup> ug = userService.getUserGroups(userService.getCurrentUser());
        UserGroup[] uga;
        uga = new UserGroup[ug.size()];
        uga = ug.toArray(uga);
        model.addAttribute("userList", uga.length==0 ? new ArrayList<User>() : userService.getGroupsUsersWithoutCurrent(ug.toArray(uga)));
        return "home/newChat";
    }

    //  creating new chat from main messages' page
    @RequestMapping(value = "/newChat", method = RequestMethod.POST)
    public String createNewChatFromMainPage(Chat chat, Model model, Locale locale) {
        if (!Objects.equals(chat.getChatTitle(), "") && !chat.getMembers().isEmpty()) {
            Set<User> members = chat.getMembers();
            members.add(userService.getCurrentUser());   // adding chat's creator as a member of the chat
            chat.setMembers(members);
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
        if (!messages.isEmpty()) {
            Message lastMessage = messages.get(messages.size()-1);
            if (!lastMessage.isReadStatus() && !currentUser.equals(lastMessage.getSender())){
                lastMessage.setReadStatus(true);
                messageService.saveMessage(lastMessage);
            }
            messages = messageService.findMessagesByChatOrderByCreatedAt(chatId);
            model.addAttribute("messagesList", messages);
        }
        if (!chat.getMembers().contains(currentUser)) {
            return "redirect:/home/messages";
        }
        if (chat.getChatTitle().equals("dialog")) {
            for (User u : chat.getMembers()) {
                if (!u.equals(currentUser)) {
                    chat.setChatTitle(u.getFirstName() + " " + u.getLastName());
                }
            }
            model.addAttribute("dialog", true);
        }
//      adding owner permissions to the chat view
        if (currentUser.equals(chat.getOwner())) {
            model.addAttribute("ownerPermission", true);
        }
        if (currentUser.getRole().getRoleName().equals("ADMIN")) {
            model.addAttribute("ownerPermission", true);
        }
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("ownerPermission", false);
        model.addAttribute(chat);
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
        for (User u : userService.getGroupsUsers( ug.toArray(new UserGroup[ug.size()]))) {
            if (!chatMembers.contains(u)) {
                membersToAdd.add(u);
            }
        }
        model.addAttribute("chat", currentChat);
        model.addAttribute("membersToAdd", membersToAdd);
        model.addAttribute("newMembers", new HashSet<User>());
        model.addAttribute("status", "add");
        return "home/editChat";
    }

    //  adding new members to certain chat
    @RequestMapping(value = "/{chatId}/addMembers", method = RequestMethod.POST)
    public String addNewMembersToChat(@PathVariable("chatId") Long chatId,
                                      Model model,
//                                      @RequestParam(value = "friends[]", required = false) String[] newMembers,
                                      HashSet<User> newMembers,
                                      Chat currentChat) {
        HashSet<User> newMembers2 =  newMembers;
        if (!newMembers.isEmpty()) {
            User inviter = userService.getCurrentUser();
            messageService.addNewMembersToChat(newMembers, currentChat);
//            !!!!!!!!!!!!!!!!!! ТУТ УСТАНОВЛЕН ЯЗЫК УКР ПО ДЕФОЛТУ
            messageService.sendMessage(inviter, messageService.getAddingNewMemberMessage(inviter, newMembers, currentChat, new Locale("UK")));
            model.addAttribute("chatId", chatId);
            return "redirect:/home/messages/{chatId}";
        } else {
            model.addAttribute("message", messages.getMessage("message.addingNewMemberToChatError", null, new Locale("UK")));
            return "redirect:/home/messages/{chatId}";
        }
    }

    //   get page to edit chat
    @RequestMapping(value = "/{chatId}/edit", method = RequestMethod.GET)
    public String getChatEditPage(@PathVariable("chatId") Long chatId, Model model) {
        Chat chat = messageService.findOneChat(chatId);
        User currentUser = userService.getCurrentUser();
        model.addAttribute("chat", chat);
        model.addAttribute("chatId", chatId);
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("status", "edit");
        return "home/editChat";
    }

    //  change chat title
    @RequestMapping(value = "/{chatId}/changeTitle", method = RequestMethod.POST)
    public String changeTitleOfChat(@PathVariable("chatId") Long chatId, Chat chat, Model model) {
        if (!chat.getChatTitle().equals("") && !messageService.findOneChat(chatId).getChatTitle().equals(chat.getChatTitle())) {
            User changer = userService.getCurrentUser();
            messageService.sendMessage(changer, messageService.getChangingTitleMessage(changer, chat, new Locale("UK")));
            messageService.saveChat(chat);
        }
        model.addAttribute("chatId", chatId);
        return "redirect:/home/messages/{chatId}";
    }

//    delete member from chat
    @RequestMapping(value = "/{chatId}/deleteMember/{userId}", method = RequestMethod.GET)
    public String deleteMembersFromChat(@PathVariable("chatId") Long chatId, @PathVariable("userId") Long userId) {
        messageService.deleteMemberFromChat(userService.findOne(userId), messageService.findOneChat(chatId));
        return "redirect:/home/messages/{chatId}/edit";
    }

//    delete chat
    @RequestMapping(value = "/{chatId}/deleteChat", method = RequestMethod.GET)
    public String deleteChat(@PathVariable("chatId") Long chatId, Model model) {
        Chat chat = messageService.findOneChat(chatId);
        User currentUser = userService.getCurrentUser();
        if (chat.getOwner().equals(currentUser) || currentUser.getRole().getRoleName().equals("ADMIN")) {
            messageService.deleteChat(chatId);
            model.addAttribute("message", "Бесіду видалено");
            return "redirect:/home/messages";
        }
        model.addAttribute("message", "permission denied");
        return "redirect:/home/messages";
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
        User currentUser = userService.getCurrentUser();
        if (currentUser.getUserId().equals(userId)) {
            return "redirect:/home/messages";
        }
        Chat currentChat = messageService.findDialog(currentUser.getUserId());
        if (currentChat == null) {
            currentChat = messageService.saveDialog(new User[]{currentUser, userService.findOne(userId)});
        }
        model.addAttribute("chatId", currentChat.getChatId());
        return "redirect:/home/messages/{chatId}";
    }
}
