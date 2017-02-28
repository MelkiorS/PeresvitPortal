package ua.peresvit.testdata;

import com.google.common.collect.Lists;
import ua.peresvit.entity.Article;
import ua.peresvit.entity.ResourceGroupType;
import ua.peresvit.entity.ResourceGroupTypeChapter;
import ua.peresvit.entity.ResourceGroupTypeChapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maximmaximov_2 on 2/21/17.
 */
public class ResourceGroupTypeChapterFactory implements BaseFactory<ResourceGroupTypeChapter> {


    @Override
    public ResourceGroupTypeChapter getFirst() {
        ResourceGroupTypeChapter res = new ResourceGroupTypeChapter();
        res.setChapterId(1l);
        res.setResourceGroupType(null);
        res.setChapterName("Chapter 1");
        res.setArticleCollection(Lists.newArrayList(new ArticleFactory().getFirst()));
        return res;
    }

    @Override
    public ResourceGroupTypeChapter getSecond() {
        ResourceGroupTypeChapter res = new ResourceGroupTypeChapter();
        res.setChapterId(2l);
        res.setResourceGroupType(null);
        res.setChapterName("Chapter 2");
        res.setArticleCollection(Lists.newArrayList(new ArticleFactory().getSecond()));
        return res;
    }

    @Override
    public ResourceGroupTypeChapter getNew() {
        ResourceGroupTypeChapter res = new ResourceGroupTypeChapter();
        res.setChapterId(3l);
        res.setResourceGroupType(null);
        res.setChapterName("Chapter 3");
        res.setArticleCollection(new ArrayList<Article>());
        return res;
    }

    @Override
    public List<ResourceGroupTypeChapter> getAll() {
        return Lists.newArrayList(getFirst(), getSecond());
    }
}
