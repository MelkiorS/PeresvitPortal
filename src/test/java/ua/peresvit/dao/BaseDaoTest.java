package ua.peresvit.dao;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ua.peresvit.entity.Role;
import ua.peresvit.testdata.BaseFactory;
import ua.peresvit.testdata.RoleFactory;

import java.util.List;

/**
 * Created by maximmaximov_2 on 2/19/17.
 */
public abstract class BaseDaoTest<T> {

    private JpaRepository<T, Long> r;
    private BaseFactory<T> f;

    public void init(JpaRepository<T, Long> r, BaseFactory<T> f) {
        this.r = r;
        this.f = f;
    }

    @Test
    public void testFindOne() {
        Assert.assertEquals(f.getFirst(), r.findOne(1l));
    }

    @Test
    public void testFindAll() {

        Assert.assertEquals(f.getAll(), r.findAll());
    }

    @Test
    @Transactional
    public void testDelete() {
        T nr = f.getSecond();
        r.delete(nr);
        List<T> l = f.getAll();
        l.remove(nr);

        Assert.assertEquals(l, r.findAll());
    }

    @Test
    @Transactional
    public void testSave() {
        T nr = f.getNew();

        List<T> all = f.getAll();

        all.add(nr);
        r.save(f.getNew());

        Assert.assertThat(all, CoreMatchers.is(r.findAll()));

    }
}
