package com.mywon.resumeportal;


import java.util.Optional;

import com.mywon.resumeportal.models.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Integer>{

    Optional<UserProfile> findByUserName(String userName);
    
}
