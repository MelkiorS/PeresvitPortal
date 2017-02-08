package ua.peresvit.service;

import ua.peresvit.entity.Chat;
import ua.peresvit.entity.Message;
import ua.peresvit.entity.User;

import java.util.*;

public interface MessageService {
    Message saveMessage(Message message);

    Chat saveChat(Chat chat);

    Chat saveDialog(User[] users);

    Chat findOneChat(Long arg0);

    Chat findDialog(User user);

    Chat createNewChat(Chat chat, Locale locale);

    Message getNewChatCreatingMessage(User creator, Chat chat, Locale locale);

    Chat addNewMembersToChat(LinkedList<User> membersToAdd, Chat chat);

    Message getAddingNewMemberMessage(User adder, LinkedList<User> listOfNewMembers, Chat chat, Locale locale);

    Message sendMessage(User from, Message message);

//    LinkedList<Message> findAllByAuthorAndReceiver(User author, User receiver);

    List<Message> findMessagesByChatOrderByCreatedAt(Long chatId);

    Set<Chat> findUserChats(User user);

    long countUnreadChats();
}
