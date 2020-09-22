package com.shopy.shopybe.repository;

import com.shopy.shopybe.entity.ApplicationRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRoleRepository extends CrudRepository<ApplicationRole, Integer> {
}
