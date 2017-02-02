package ua.peresvit.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ua.peresvit.dao.ChatRepository;
import ua.peresvit.dao.MessageRepository;
import ua.peresvit.entity.Chat;
import ua.peresvit.entity.Message;
import ua.peresvit.entity.User;
import ua.peresvit.service.MessageService;

import java.sql.Timestamp;
import java.util.*;

@Service
public class MessageServiceImpl implements MessageService{

    final MessageRepository messageRepository;

    final ChatRepository chatRepository;

    final MessageSource messages;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository, ChatRepository chatRepository, MessageSource messages) {
        this.messageRepository = messageRepository;
        this.chatRepository = chatRepository;
        this.messages = messages;
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
    public Chat findDialog(Collection<User> members) {
        return chatRepository.findByMembersIn(members);
    }

    @Override
    public Message getNewChatCreatingMessage(User creator, Chat chat, Locale locale) {
        Message message = new Message();
        message.setSender(creator);
        message.setChat(chat);
        message.setFunctional(true);
        message.setContent(creator.getFirstName() + " " + creator.getLastName() + " "
                + messages.getMessage("message.newChatCreationEvent", null, locale) + " "
                + "<" + chat.getChatTitle() + ">");
        return null;
    }

    @Override
    public Chat addNewMembersToChat(LinkedList<User> membersToAdd, Chat chat) {
        Set<User> members = chat.getMembers();
        for (User u : membersToAdd) {
            members.add(u);
        }
        chat.setMembers(members);
        return saveChat(chat);
    }

    @Override
    public Message getAddingNewMemberMessage(User adder, LinkedList<User> listOfNewMembers, Chat chat, Locale locale) {
        Message message = new Message();
        message.setSender(adder);
        message.setChat(chat);
        message.setFunctional(true);
//

        Iterator<User> it = listOfNewMembers.iterator();
        StringBuilder newMembers = new StringBuilder();
        for (int i = 0; i != 1;) {
            User u = it.next();
            newMembers.append(u.getFirstName()).append(" ").append(u.getLastName());
            if (! it.hasNext()){
                i = 1;
            } else {
                newMembers.append(',').append(' ');
            }
        }
        message.setContent(adder.getFirstName() + " " + adder.getLastName() + " "
                + messages.getMessage("message.addingNewMembersEvent", null, locale) + " "
                + newMembers);
        return null;
    }

    @Override
    public Message sendMessage(User from, Message message) {
        message.setSender(from);
        message.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        return saveMessage(message);
    }

//    @Override
//    public LinkedList<Message> findAllByAuthorAndReceiver(User author, User receiver) {
//        return findAllByAuthorAndReceiver(author, receiver);
//    }

    @Override
    public List<Message> findChatOrderByCreatedAt(Long chatId) {
        return messageRepository.findByChatOrderByCreatedAt(chatId);
    }

    @Override
    public List<Chat> findAllUsersChats(User user) {
        return chatRepository.findAllUsersChats(user);
    }

    @Override
    public long countUnreadChats() {
        return 0;
    }
}
