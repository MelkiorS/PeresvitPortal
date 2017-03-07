package ua.peresvit.testdata;

import com.google.common.collect.Lists;
import ua.peresvit.entity.Post;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by maximmaximov_2 on 2/21/17.
 */
public class PostFactory implements BaseFactory<Post> {

    @Override
    public Post getFirst() {
        Post res = new Post();
        res.setPostId(1l);
        res.setTitle("Post 1");
        res.setBody("Body 1");
        res.setUrl("");
        res.setCreateDate(Timestamp.valueOf("2017-01-01 00:00:00"));
        return res;
    }

    @Override
    public Post getSecond() {
        Post res = new Post();
        res.setPostId(2l);
        res.setTitle("Post 2");
        res.setBody("Body 2");
        res.setUrl("");
        res.setCreateDate(Timestamp.valueOf("2017-02-01 00:00:00"));
        return res;
    }

    @Override
    public Post getNew() {
        Post res = new Post();
        res.setPostId(3l);
        res.setTitle("Post 3");
        res.setBody("Body 3");
        res.setUrl("");
        res.setCreateDate(Timestamp.valueOf("2017-03-01 00:00:00"));
        return res;
    }

    @Override
    public List<Post> getAll() {
        return Lists.newArrayList(getFirst(), getSecond());
    }
}
