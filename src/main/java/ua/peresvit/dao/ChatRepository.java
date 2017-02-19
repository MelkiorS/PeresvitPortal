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

    @Query("select distinct c from User u " +
            "INNER JOIN u.chats c " +
            "INNER JOIN c.members m " +
            "where ((select count(m) from c) = 2) and (u.userId = :userId)")
    Set<Chat> findDialogsOfUser(@Param("userId") Long userId);

    @Query("select new ua.peresvit.dto.ChatWithLastMessage(c.chatId, c.chatTitle, m.messageId, m.content, m.createdAt, m.readStatus, m.sender) " +
            "from User u " +
            "left outer join u.chats c " +
            "left outer join c.messages m " +
            "where (m.createdAt = (select max(m.createdAt) from m where m.chat = c)) and (u = :user) " +
            "order by m.readStatus, m.createdAt desc")
    Set<ChatWithLastMessage> getChatsWithLastMessage(@Param("user") User user);
}
