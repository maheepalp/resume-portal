package com.mywon.resumeportal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mywon.resumeportal.models.Job;
import com.mywon.resumeportal.models.UserProfile;

@Controller
public class HomeController {

    @Autowired
    UserProfileRepository userProfileRepository;

    @GetMapping("/")
    public String home(){
        UserProfile profile1 = new UserProfile();
        profile1.setId(1);
        profile1.setDesignation("profile designation");
        profile1.setUserName("newton");
        profile1.setFirstName("Newton");
        profile1.setLastName("Last Name");
        profile1.setTheme(1);

        Job job1 = new Job();
        job1.setCompany("company 1");
        job1.setDesignation("designation 1");
        job1.setId(1);
        job1.setStartDate(LocalDate.of(2020, 1,1));
        job1.setEndDate(LocalDate.of(2020, 3,17));

        Job job2 = new Job();
        job2.setCompany("company 2");
        job2.setDesignation("designation 2");
        job2.setId(2);
        job2.setStartDate(LocalDate.of(2022, 1,1));
        job2.setEndDate(LocalDate.of(2022, 3,17));
        

        profile1.setJobs(Arrays.asList(job1,job2));

        userProfileRepository.save(profile1);
        
        return "Hello!!";

    }

    @GetMapping("/edit")
    public String edit(){
        return "Edit!!";

    }

    @GetMapping("/view/{userId}")
    public String view(@PathVariable("userId") String aaa, Model model){
        Optional<UserProfile> profileOpt = userProfileRepository.findByUserName(aaa);
        profileOpt.orElseThrow( () -> new RuntimeException("profile not found "+aaa));

        UserProfile profile = profileOpt.get();

        model.addAttribute("userId", aaa);
        model.addAttribute("userProfile", profile);
        
        System.out.println(profile.getJobs());
        
        return "./"+profile.getTheme()+"/index";

    }
}
