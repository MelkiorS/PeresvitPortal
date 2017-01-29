package ua.peresvit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.peresvit.entity.Chat;
import ua.peresvit.entity.Message;
import ua.peresvit.entity.User;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long> {

    <S extends Chat> S save(S arg0);

    Chat findOne(Long arg0);

    @Query("select distinct c from Chat c INNER JOIN c.members m where m = :user")
    List<Chat> findAllUsersChats(@Param("user") User user);

    @Query("select first 1 * from Chat c INNER JOIN c.messages m where c = :chat order by m.createdAt")
    Message findLastMessageFromChat(@Param("chat") Chat chat);
}
