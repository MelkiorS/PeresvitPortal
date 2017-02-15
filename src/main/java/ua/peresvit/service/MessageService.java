package ua.peresvit.service;

import ua.peresvit.dto.ChatWithLastMessage;
import ua.peresvit.entity.Chat;
import ua.peresvit.entity.Message;
import ua.peresvit.entity.User;

import java.util.*;

public interface MessageService {
    Message saveMessage(Message message);

    Chat saveChat(Chat chat);

    Chat saveDialog(User[] users);

    void deleteChat(Long chatId);

    Chat findOneChat(Long arg0);

    Chat findDialog(User user);

    Chat createNewChat(Chat chat, Locale locale);

    Message getNewChatCreatingMessage(User creator, Chat chat, Locale locale);

    Chat addNewMembersToChat(LinkedList<User> membersToAdd, Chat chat);

    Chat deleteMemberFromChat(User user, Chat chat);

    Message getAddingNewMemberMessage(User adder, LinkedList<User> listOfNewMembers, Chat chat, Locale locale);

    Message sendMessage(User from, Message message);

    List<Message> findMessagesByChatOrderByCreatedAt(Long chatId);

    Set<ChatWithLastMessage> findCustomChatsOfUser(User user);

    Message findLastMessage(Chat chat);

    long countUnreadChats();
}
