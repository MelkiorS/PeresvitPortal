package org.bionic.dao;

import org.bionic.entity.Article;
import org.bionic.entity.Rang;
import org.bionic.entity.ResourceGroupType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    <S extends Article> S save(S arg0);

    Article findOne(Long arg0);

    java.util.List<Article> findAll();

    void delete(Article arg0);

    boolean equals(Object obj);

    Article findByArticleId(String resourceGroupId);

    Collection<Article> findAllByResourceGroupTypeAndRang(ResourceGroupType type, Rang rang);

    Article findByChapterId(long chapterId);
}