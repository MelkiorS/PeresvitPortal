package org.bionic.service;


import org.bionic.entity.Article;
import org.bionic.entity.Rang;
import org.bionic.entity.ResourceGroupType;

import java.util.Collection;

public interface ArticleService {

    <S extends Article> S save(S arg0);

    Article findOne(Long arg0);

    java.util.List<Article> findAll();

    void delete(Article arg0);

    boolean equals(Object obj);

    Collection<Article> findAllByResourceGroupTypeAndRang(ResourceGroupType type, Rang rang);

    Collection<Article> findAllByChapterIdAndResourceGroupTypeAndRang(long chapterId, ResourceGroupType type, Rang rang);

    Article findByChapterId(long chapterId);
}
