package ua.peresvit.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.peresvit.dao.ChatRepository;
import ua.peresvit.dao.MessageRepository;
import ua.peresvit.entity.Chat;
import ua.peresvit.entity.Message;
import ua.peresvit.entity.User;
import ua.peresvit.service.MessageService;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService{

    final
    MessageRepository messageRepository;

    final
    ChatRepository chatRepository;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository, ChatRepository chatRepository) {
        this.messageRepository = messageRepository;
        this.chatRepository = chatRepository;
    }

    @Override
    public <S extends Message> S saveMessage(S arg0) {
        return messageRepository.save(arg0);
    }

    @Override
    public <S extends Chat> S saveChat(S arg0) {
        return chatRepository.save(arg0);
    }

    @Override
    public Chat findOneChat(Long arg0) {
        return chatRepository.findOne(arg0);
    }

    @Override
    public Chat createNewChat(String chatTitle, List<User> members) {
        return null;
    }

    @Override
    public Message sendMessage(User from, Message message) {
        message.setSender(from);
        message.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        return saveMessage(message);
    }

    @Override
    public LinkedList<Message> findAllByAuthorAndReceiver(User author, User receiver) {
        return findAllByAuthorAndReceiver(author, receiver);
    }

    @Override
    public List<Message> findChatOrderByCreatedAt(Long chatId) {
        return messageRepository.findByChatOrderByCreatedAt(chatId);
    }

    @Override
    public List<Chat> findAllUsersChats(User user) {
        return chatRepository.findAllUsersChats(user);
    }

    @Override
    public long countUnreadChats(User user) {
        return 0;
    }
}
