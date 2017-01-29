package ua.peresvit.service;

import ua.peresvit.entity.Chat;
import ua.peresvit.entity.Message;
import ua.peresvit.entity.User;

import java.util.LinkedList;
import java.util.List;

public interface MessageService {
    <S extends Message> S saveMessage(S arg0);

    <S extends Chat> S saveChat(S arg0);

    Chat findOneChat(Long arg0);

    Chat createNewChat(String chatTitle, List<User> members);

    Message sendMessage(User from, Message message);

    LinkedList<Message> findAllByAuthorAndReceiver(User author, User receiver);

    List<Message> findChatOrderByCreatedAt(Long chatId);

    List<Chat> findAllUsersChats(User user);

    long countUnreadChats(User user);
}
