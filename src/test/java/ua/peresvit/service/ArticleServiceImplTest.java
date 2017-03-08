package ua.peresvit.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ActiveProfiles;
import ua.peresvit.dao.ArticleRepository;
import ua.peresvit.entity.Article;
import ua.peresvit.service.impl.ArticleServiceImpl;
import ua.peresvit.testdata.ArticleFactory;
import static org.mockito.Mockito.when;

/**
 * Created by maximmaximov_2 on 3/1/17.
 */
@ActiveProfiles("test")
@RunWith(MockitoJUnitRunner.class)
public class ArticleServiceImplTest {

    @Spy
    @InjectMocks
    private ArticleServiceImpl s = new ArticleServiceImpl();

    @Mock
    private ArticleRepository r;

    private Article a = new ArticleFactory().getFirst();

    @Test
    public void save() {
        when(r.save(a)).thenReturn(a);
        Assert.assertEquals(s.save(a), a);
    }


}
