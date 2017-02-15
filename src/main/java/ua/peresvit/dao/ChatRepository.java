package ua.peresvit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.peresvit.dto.ChatWithLastMessage;
import ua.peresvit.entity.Chat;
import ua.peresvit.entity.User;

import java.util.List;
import java.util.Set;

public interface ChatRepository extends JpaRepository<Chat, Long> {

    <S extends Chat> S save(S arg0);

    Chat findOne(Long arg0);

    void delete(Long aLong);

    @Query("select distinct c from User u INNER JOIN u.chats c where u = :user")
    Set<Chat> findChatsOfUser(@Param("user") User user);

    @Query("select c from User u INNER JOIN u.chats c where (u = :user) AND ((select count (distinct m) from c inner join c.members m ) = 2)")
    Set<Chat> findDialogsOfUser(@Param("user") User user);

    @Query("select new ua.peresvit.dto.ChatWithLastMessage(c.chatId, c.chatTitle, m.messageId, m.content, m.createdAt, m.readStatus, m.sender) " +
            "from User u " +
            "left outer join u.chats c " +
            "left outer join c.messages m " +
            "where (m.createdAt = (select max(mes.createdAt) from Message mes where mes.chat = c)) and (u = :user) " +
            "order by m.createdAt desc")
    Set<ChatWithLastMessage> getChatsWithLastMessage(@Param("user") User user);
}
