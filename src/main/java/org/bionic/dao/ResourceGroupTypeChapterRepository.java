package org.bionic.dao;

import org.bionic.entity.ResourceGroupType;
import org.bionic.entity.ResourceGroupTypeChapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceGroupTypeChapterRepository extends JpaRepository<ResourceGroupTypeChapter, Long> {
    <S extends ResourceGroupTypeChapter> S save(S arg0);

    ResourceGroupTypeChapter findOne(Long arg0);

    java.util.List<ResourceGroupTypeChapter> findAll();

    void delete(ResourceGroupTypeChapter arg0);

    boolean equals(Object obj);

    List<ResourceGroupTypeChapter> findAllByResourceGroupType(ResourceGroupType id);
}