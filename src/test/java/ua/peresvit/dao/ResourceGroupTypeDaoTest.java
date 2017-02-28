package ua.peresvit.dao;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.peresvit.config.PersistenceTestConfig;
import ua.peresvit.entity.City;
import ua.peresvit.entity.ResourceGroupType;
import ua.peresvit.testdata.CityFactory;
import ua.peresvit.testdata.ResourceGroupTypeFactory;

/**
 * Created by maximmaximov_2 on 2/21/17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceTestConfig.class}, loader = AnnotationConfigContextLoader.class)
@ActiveProfiles("test")
@Transactional(propagation = Propagation.REQUIRED)
@Rollback(true)
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:populateTest.sql")
public class ResourceGroupTypeDaoTest extends BaseDaoTest<ResourceGroupType> {

    @Autowired
    private ResourceGroupTypeRepository cr;

    @Before
    public void setup() {
        super.init(cr, new ResourceGroupTypeFactory());
    }


}
