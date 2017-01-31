package ua.peresvit.service;

import ua.peresvit.entity.Chat;
import ua.peresvit.entity.Message;
import ua.peresvit.entity.User;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public interface MessageService {
    <S extends Message> S saveMessage(S arg0);

    <S extends Chat> S saveChat(S arg0);

    Chat findOneChat(Long arg0);

    Chat findDialog(Collection<User> members);

    Message getNewChatCreatingMessage(User creator, Chat chat, Locale locale);

    Chat addNewMembersToChat(LinkedList<User> membersToAdd, Chat chat);

    Message getAddingNewMemberMessage(User adder, LinkedList<User> listOfNewMembers, Chat chat, Locale locale);

    Message sendMessage(User from, Message message);

//    LinkedList<Message> findAllByAuthorAndReceiver(User author, User receiver);

    List<Message> findChatOrderByCreatedAt(Long chatId);

    List<Chat> findAllUsersChats(User user);

    long countUnreadChats();
}
