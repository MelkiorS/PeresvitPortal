package ua.peresvit.testdata;

import com.google.common.collect.Lists;
import ua.peresvit.entity.ResourceGroupType;
import ua.peresvit.entity.ResourceGroupType;
import ua.peresvit.entity.ResourceGroupTypeChapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maximmaximov_2 on 2/21/17.
 */
public class ResourceGroupTypeFactory implements BaseFactory<ResourceGroupType> {


    @Override
    public ResourceGroupType getFirst() {
        ResourceGroupType res = new ResourceGroupType();
        res.setResourceGroupTypeId(1l);
        res.setGroupName("Type1");
        res.setCaption("Type 1");
        res.setChapterList(new ArrayList<ResourceGroupTypeChapter>());
        return res;
    }

    @Override
    public ResourceGroupType getSecond() {
        ResourceGroupType res = new ResourceGroupType();
        res.setResourceGroupTypeId(2l);
        res.setGroupName("Type2");
        res.setCaption("Type 2");
        res.setChapterList(new ArrayList<ResourceGroupTypeChapter>());
        return res;
    }

    @Override
    public ResourceGroupType getNew() {
        ResourceGroupType res = new ResourceGroupType();
        res.setResourceGroupTypeId(3l);
        res.setGroupName("Type3");
        res.setCaption("Type 3");
        res.setChapterList(new ArrayList<ResourceGroupTypeChapter>());
        return res;
    }

    @Override
    public List<ResourceGroupType> getAll() {
        return Lists.newArrayList(getFirst(), getSecond());
    }
}
