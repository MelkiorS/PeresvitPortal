package org.bionic.service;

import org.bionic.entity.ResourceGroupType;
import org.bionic.entity.ResourceGroupTypeChapter;

import java.util.List;

public interface ResourceGroupTypeChapterService {
    <S extends ResourceGroupTypeChapter> S save(S arg0);

    ResourceGroupTypeChapter findOne(Long arg0);

    java.util.List<ResourceGroupTypeChapter> findAll();

    void delete(ResourceGroupTypeChapter arg0);;

    List<ResourceGroupTypeChapter> findAllByResourceGroupType(ResourceGroupType id);

    boolean equals(Object obj);
}
