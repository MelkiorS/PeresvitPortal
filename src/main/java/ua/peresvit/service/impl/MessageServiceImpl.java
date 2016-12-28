package ua.peresvit.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.peresvit.dao.MessageRepository;
import ua.peresvit.entity.Message;
import ua.peresvit.entity.User;
import ua.peresvit.service.MessageService;

import java.util.LinkedList;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService{

    @Autowired
    MessageRepository messageRepository;

    @Override
    public <S extends Message> S save(S arg0) {
        return messageRepository.save(arg0);
    }

    @Override
    public Message findOne(Long arg0) {
        return messageRepository.findOne(arg0);
    }

    @Override
    public List<Message> findAllByAuthor(User author) {
        return messageRepository.findAllByAuthor(author);
    }

    @Override
    public List<Message>  findAllByReceiver(User receiver) {
        return messageRepository.findAllByReceiver(receiver);
    }

    @Override
    public Message sendMessage(User from, User to, Message message) {
        message.setAuthor(from);
        message.setReceiver(to);
        return save(message);
    }

    @Override
    public LinkedList<Message> findAllByAuthorAndReceiver(User author, User receiver) {
        return findAllByAuthorAndReceiver(author, receiver);
    }
}
