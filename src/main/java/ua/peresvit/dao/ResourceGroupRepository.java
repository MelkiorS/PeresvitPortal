package ua.peresvit.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import ua.peresvit.entity.ResourceGroup;
import ua.peresvit.entity.ResourceGroupType;
import ua.peresvit.entity.Role;

public interface ResourceGroupRepository extends JpaRepository<ResourceGroup, Long> {
	<S extends ResourceGroup> S save(S arg0);

	ResourceGroup findOne(Long arg0);

	java.util.List<ResourceGroup> findAll();

	void delete(ResourceGroup arg0);

	boolean equals(Object obj);

	ResourceGroup findByResourceGroupId(String resourceGroupId);

    ResourceGroup findResourceGroupByResourceGroupTypeAndRang(ResourceGroupType type, Role role);
}
