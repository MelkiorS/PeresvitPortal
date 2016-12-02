package org.bionic.service.impl;

import org.bionic.dao.ArticleRepository;
import org.bionic.entity.Article;
import org.bionic.entity.Rang;
import org.bionic.entity.ResourceGroupType;
import org.bionic.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ArticleServiceImpl implements ArticleService{
    @Autowired
    ArticleRepository articleRepository;

    public <S extends Article> S save(S arg0) {
        return articleRepository.save(arg0);
    }


    public Article findOne(Long arg0) {
        return articleRepository.findOne(arg0);
    }

    public java.util.List<Article> findAll() {
        return articleRepository.findAll();
    }

    public void delete(Article arg0) {
        articleRepository.delete(arg0);
    }

    public Collection<Article> findAllByResourceGroupTypeAndRang(ResourceGroupType type, Rang rang){
        return articleRepository.findAllByResourceGroupTypeAndRang(type, rang);
    }

    public Article findByChapterId(long chapterId) {
        return articleRepository.findByChapterId(chapterId);
    }

    public Collection<Article> findAllByChapterIdAndResourceGroupTypeAndRang(long chapterId, ResourceGroupType type, Rang rang) {
        return articleRepository.findAllByChapterIdAndResourceGroupTypeAndRang(chapterId,type,rang);
    }
}
