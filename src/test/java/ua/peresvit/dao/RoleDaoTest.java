package ua.peresvit.dao;

import org.hamcrest.CoreMatchers;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.notification.RunListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.SecurityConfig;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import ua.peresvit.config.*;
import ua.peresvit.entity.Role;
import ua.peresvit.service.RoleService;
import ua.peresvit.testdata.RoleFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maximmaximov_2 on 2/6/17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceTestConfig.class}, loader = AnnotationConfigContextLoader.class)
@ActiveProfiles("test")
@Transactional(propagation = Propagation.REQUIRED)
@Rollback(true)
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:populateTest.sql")
public class RoleDaoTest extends BaseDaoTest<Role> {

    @Autowired
    private RoleRepository rr;

    @Before
    public void setup() {
        super.init(rr, new RoleFactory());
    }

    @Override
    public void testDelete() {

    }
}
