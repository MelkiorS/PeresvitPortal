package org.bionic.dao;

import java.util.List;

import org.bionic.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository  extends JpaRepository<UserInfo, Long>{
	
	public List<UserInfo> findByUser_id(Long userId);
	
}
