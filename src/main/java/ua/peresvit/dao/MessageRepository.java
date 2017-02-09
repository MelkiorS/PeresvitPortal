package ua.peresvit.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.peresvit.entity.Chat;
import ua.peresvit.entity.Message;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    <S extends Message> S save(S arg0);

    Message findOne(Long arg0);

    @Query("select m from Message m where m.chat.chatId = :chatId order by m.createdAt")
    List<Message> findByChatOrderByCreatedAt(@Param("chatId") Long chatId);

    List<Message> findByChatOrderByCreatedAt(Chat chat);

    @Query("select m from Chat c inner join c.messages m where c = :chat and m.createdAt = (select max(m.createdAt) from m)")
    Message findLastMessage(@Param("chat")Chat chat);

//    @Query("select m from Message m where")
//    List<Message> findByMembersOrderByCreatedAt()
}
