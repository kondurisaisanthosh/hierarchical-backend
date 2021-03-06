package com.hierarchical.HierProject.repos;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hierarchical.HierProject.model.Organization;

@Repository
public interface OrganizationRepo extends JpaRepository <Organization,String>{
	
	@Query(value="SELECT * FROM organization_table where organization_name=(?1)",nativeQuery=true)
	public Organization getOgranization(String orgName); 

	
	@Modifying
	@Query(value="INSERT INTO organization_table(organization_name)VALUES(?1)",nativeQuery=true)
	@Transactional
	public void storeOrganization(String orgName);
	
	@Query(value="SELECT count(1) FROM organization_table WHERE organization_name=?1",nativeQuery=true)
	public int checkOrg(String organizationName);
	
	@Modifying
	@Query(value="delete FROM organization_table where organization_UUID=(?1)",nativeQuery=true)
	@Transactional
	public void deleteOrganization(String organization_UUID);
}
