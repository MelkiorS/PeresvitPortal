package ua.peresvit.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import ua.peresvit.entity.Post;

import java.util.List;

public interface PostService {

    <S extends Post> S save(S arg0);

    String saveFile(Post post, MultipartFile file);

    Post findOne(Long cityId);

    List<Post> findAll();
    Page<Post> findAll(Pageable pageable);
}
