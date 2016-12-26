package ua.peresvit.dao;

import ua.peresvit.entity.Mark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface MarkRepository extends JpaRepository<Mark, Long>{

    <S extends Mark> S save(S arg0);

    Mark findOne(Long arg0);

    List<Mark> findAll();

    //List<User> findAllUsers();

}
