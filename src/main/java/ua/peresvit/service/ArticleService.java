package ua.peresvit.service;



import ua.peresvit.entity.Article;
import ua.peresvit.entity.ResourceGroupType;
import ua.peresvit.entity.Role;

import java.util.Collection;

public interface ArticleService {

    <S extends Article> S save(S arg0);

    Article findOne(Long arg0);

    java.util.List<Article> findAll();

    void delete(Article arg0);

    boolean equals(Object obj);

    Collection<Article> findAllByResourceGroupTypeAndRang(ResourceGroupType type, Role role);

    Collection<Article> findAllByChapterIdAndResourceGroupTypeAndRang(long chapterId, ResourceGroupType type, Role role);

    public Collection<Article> findAllByChapterIdAndResourceGroupType(long chapterId, ResourceGroupType type);

    Article findByChapterId(long chapterId);
}
