package ua.peresvit.service;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.multipart.MultipartFile;
import ua.peresvit.dao.AchievementRepository;
import ua.peresvit.entity.Achievement;
import ua.peresvit.service.impl.AchievementServiceImpl;
import ua.peresvit.testdata.AchievementFactory;
import ua.peresvit.testdata.UserFactory;

import java.io.IOException;
import java.io.InputStream;

import static org.mockito.Mockito.when;

/**
 * Created by maximmaximov_2 on 3/1/17.
 */
@ActiveProfiles("test")
@RunWith(MockitoJUnitRunner.class)
public class AchievementServiceImplTest {

    @Spy
    @InjectMocks
    private AchievementService service = new AchievementServiceImpl();

    @Mock
    private AchievementRepository achievementRepository;

    @Mock
    private Achievement obj;

    @Mock
    private MultipartFile mpf;

    private AchievementFactory f = new AchievementFactory();

    public void init(AchievementService service, Achievement obj) {
        this.service = service;
        this.obj = obj;
    }

    @Test
    public void save() {
        when(achievementRepository.save(obj)).thenReturn(obj);
        Achievement o = service.save(obj);
        Assert.assertSame(obj, o);
    }

    @Test
    public void findOne() {
        when(achievementRepository.findOne(1l)).thenReturn(f.getFirst());
        Assert.assertEquals(f.getFirst(), service.findOne(1l));
    }

    @Test
    public void findAll() {
        when(achievementRepository.findAll()).thenReturn(f.getAll());
        Assert.assertEquals(f.getAll(), service.findAll());
    }

    @Test
    public void findByUser() {
        when(achievementRepository.findByUser(new UserFactory().getFirst())).thenReturn(Lists.newArrayList(f.getFirst()));
        Assert.assertEquals(service.findByUser(new UserFactory().getFirst()), Lists.newArrayList(f.getFirst()));
    }

    @Test
    public void saveFile() throws IOException {
        when(mpf.getName()).thenReturn("test.txt");
        when(mpf.isEmpty()).thenReturn(false);
        when(mpf.getInputStream()).thenReturn(new InputStream() {
            @Override
            public int read() throws IOException {
                return 123;
            }
        });
        byte[] m = new byte[1];
        m[0] = 1;
        when(mpf.getBytes()).thenReturn(m);
        service.saveFile(obj, mpf);
    }
}
