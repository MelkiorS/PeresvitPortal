package ua.peresvit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.peresvit.dao.ResourceGroupTypeChapterRepository;
import ua.peresvit.entity.ResourceGroupType;
import ua.peresvit.entity.ResourceGroupTypeChapter;
import ua.peresvit.service.ResourceGroupTypeChapterService;

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
    public List<ResourceGroupTypeChapter> findAll() {
        return resourceGroupTypeChapterRepository.findAll();
    }
    @Override
    public List<ResourceGroupTypeChapter> findAllByResourceGroupType(ResourceGroupType id) {return resourceGroupTypeChapterRepository.findAllByResourceGroupType(id);}
    @Override
    public void delete(ResourceGroupTypeChapter arg0) {
        resourceGroupTypeChapterRepository.delete(arg0);
    }
}
