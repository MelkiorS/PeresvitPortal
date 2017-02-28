package ua.peresvit.testdata;

import com.google.common.collect.Lists;
import ua.peresvit.entity.Article;
import ua.peresvit.entity.City;
import ua.peresvit.entity.ResourceGroupType;

import java.util.List;

/**
 * Created by maximmaximov_2 on 2/21/17.
 */
public class ArticleFactory implements BaseFactory<Article> {


    @Override
    public Article getFirst() {
        Article res = new Article();
        res.setArticleId(1l);
        res.setArticleName("Article 1");
        res.setContext("Context 1");
        res.setChapterId(1l);
        res.setResourceGroupType(new ResourceGroupTypeFactory().getFirst());
        res.setRole(RoleFactory.getAdmin());
        return res;
    }

    @Override
    public Article getSecond() {
        Article res = new Article();
        res.setArticleId(2l);
        res.setArticleName("Article 2");
        res.setContext("Context 2");
        res.setChapterId(2l);
        res.setResourceGroupType(new ResourceGroupTypeFactory().getFirst());
        res.setRole(RoleFactory.getUser());
        return res;
    }

    @Override
    public Article getNew() {
        Article res = new Article();
        res.setArticleId(3l);
        res.setArticleName("Article 3");
        res.setContext("Context 3");
        res.setChapterId(2l);
        res.setResourceGroupType(new ResourceGroupTypeFactory().getFirst());
        res.setRole(RoleFactory.getUser());
        return res;
    }

    @Override
    public List<Article> getAll() {
        return Lists.newArrayList(getFirst(), getSecond());
    }
}
