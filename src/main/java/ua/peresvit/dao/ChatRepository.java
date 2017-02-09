package ua.peresvit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.peresvit.dto.ChatWithLastMessage;
import ua.peresvit.entity.Chat;
import ua.peresvit.entity.Message;
import ua.peresvit.entity.User;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ChatRepository extends JpaRepository<Chat, Long> {

    <S extends Chat> S save(S arg0);

    Chat findOne(Long arg0);

    @Query("select distinct c from User u INNER JOIN u.chats c where u = :user")
    Set<Chat> findChatsOfUser(@Param("user") User user);

//    @Query("select new map(m as c) from User u inner join u.chats.c inner join c.messages where u = :user and m = ")
//    Map<Chat, Message> findChatsWithLastMessage(@Param("user")User user);
// взять два хеша чатов двох пользователей и найти их пересечение
//    @Query("select distinct c from Chat c INNER JOIN c.members m in ")
//    @Query("select distinct c from Chat c INNER JOIN c.members m where count(m) = 2 and ")
//    @Query ("select distinct c from User u INNER JOIN u.chats c where u = :user1 and c.members = :user2")
    @Query("select c from User u INNER JOIN u.chats c where (u = :user) AND ((select count (distinct m) from c inner join c.members m ) = 2)")
    Set<Chat> findDialogsOfUser(@Param("user") User user);

//    @Query("select first * from Chat c INNER JOIN c.messages m where c = :chat order by m.createdAt")
//    Message findLastMessageFromChat(@Param("chat") Chat chat);

    @Query("select new ua.peresvit.dto.ChatWithLastMessage(c.chatId, c.chatTitle, m.messageId, m.content, m.createdAt, m.readStatus, m.sender) " +
            "from User u " +
            "left outer join u.chats c " +
            "left outer join c.messages m " +
            "where (m.createdAt = (select max(mes.createdAt) from Message mes where mes.chat = c)) and (u = :user) " +
            "order by m.createdAt desc")
    Set<ChatWithLastMessage> getChatsWithLastMessage(@Param("user") User user);
}
