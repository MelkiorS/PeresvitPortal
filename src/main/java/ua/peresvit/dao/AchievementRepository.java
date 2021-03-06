package ua.peresvit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.peresvit.entity.Achievement;
import ua.peresvit.entity.User;

import java.util.List;

@Repository
@Transactional
public interface AchievementRepository extends JpaRepository<Achievement, Long> {

    <S extends Achievement> S save(S arg0);

    Achievement findOne(Long arg0);

    List<Achievement> findAll();

    List<Achievement> findByUser(User arg0);

}
