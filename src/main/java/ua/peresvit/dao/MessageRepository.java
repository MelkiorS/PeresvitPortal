package ua.peresvit.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import ua.peresvit.entity.Message;
import ua.peresvit.entity.User;

import java.util.LinkedList;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    <S extends Message> S save(S arg0);

    Message findOne(Long arg0);

    List<Message> findAllByAuthor(User author);

    List<Message>  findAllByReceiver(User receiver);

    LinkedList<Message> findAllByAuthorAndReceiver(User author, User receiver);
}
