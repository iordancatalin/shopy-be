package com.shopy.shopybe.repository;

import com.shopy.shopybe.entity.ApplicationUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationUserRepository extends CrudRepository<ApplicationUser, Long> {

    Optional<ApplicationUser> findByEmailAddress(String emailAddress);
}
