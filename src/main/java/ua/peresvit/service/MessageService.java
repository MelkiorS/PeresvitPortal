package ua.peresvit.service;

import ua.peresvit.entity.Message;
import ua.peresvit.entity.User;

import java.util.LinkedList;
import java.util.List;

public interface MessageService {
    <S extends Message> S save(S arg0);

    Message findOne(Long arg0);

    List<Message> findAllByAuthor(User author);

    List<Message>  findAllByReceiver(User receiver);

    Message sendMessage(User from, User to, Message message);

    LinkedList<Message> findAllByAuthorAndReceiver(User author, User receiver);
}
