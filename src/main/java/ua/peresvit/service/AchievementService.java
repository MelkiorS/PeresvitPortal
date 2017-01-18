package ua.peresvit.service;

import org.springframework.web.multipart.MultipartFile;
import ua.peresvit.entity.Achievement;
import ua.peresvit.entity.User;

import java.util.List;

public interface AchievementService {

    String saveFile(Achievement achievement, MultipartFile file);

    <S extends Achievement> S save(S arg0);

    Achievement findOne(Long markId);

    List<Achievement> findAll();

    List<Achievement> findByUser(User user);

}
