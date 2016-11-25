package org.bionic.service.impl;

import org.bionic.dao.ResourceGroupTypeChapterRepository;
import org.bionic.entity.ResourceGroupType;
import org.bionic.entity.ResourceGroupTypeChapter;
import org.bionic.service.ResourceGroupTypeChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceGroupTypeChapterServiceImpl implements ResourceGroupTypeChapterService {
    @Autowired
    ResourceGroupTypeChapterRepository resourceGroupTypeChapterRepository;
    @Override
    public <S extends ResourceGroupTypeChapter> S save(S arg0) {
        return resourceGroupTypeChapterRepository.save(arg0);
    }

    @Override
    public ResourceGroupTypeChapter findOne(Long arg0) {
        return resourceGroupTypeChapterRepository.findOne(arg0);
    }
    @Override
    public java.util.List<ResourceGroupTypeChapter> findAll() {
        return resourceGroupTypeChapterRepository.findAll();
    }
    @Override
    public List<ResourceGroupTypeChapter> findAllByResourceGroupType(ResourceGroupType id) {return resourceGroupTypeChapterRepository.findAllByResourceGroupType(id);}
    @Override
    public void delete(ResourceGroupTypeChapter arg0) {
        resourceGroupTypeChapterRepository.delete(arg0);
    }
}
