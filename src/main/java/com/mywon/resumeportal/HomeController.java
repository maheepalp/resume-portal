package com.mywon.resumeportal;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.mywon.resumeportal.models.Education;
import com.mywon.resumeportal.models.Job;
import com.mywon.resumeportal.models.UserProfile;

@Controller
public class HomeController {

    @Autowired
    UserProfileRepository userProfileRepository;

    @GetMapping("/")
    public String home(){

        Optional<UserProfile> opt = userProfileRepository.findByUserName("newton");
        opt.orElseThrow(() -> new RuntimeException("newton not found"));

         UserProfile profile1 = opt.get();
        // UserProfile profile1 = new UserProfile();
        // profile1.setId(1);
        // profile1.setDesignation("profile designation");
        // profile1.setUserName("newton");
        // profile1.setFirstName("Newton");
        // profile1.setLastName("Last Name");
        // profile1.setTheme(1);

        Job job1 = new Job();
        job1.setCompany("company ONE");
        job1.setDesignation("designation ONE");
        job1.setId(1);
        job1.setStartDate(LocalDate.of(2020, 1,1));
        job1.setEndDate(LocalDate.of(2020, 3,17));

        job1.getResponsibilities().add("Responsibiltiy");
        job1.getResponsibilities().add("Resp");

        Job job2 = new Job();
        job2.setCompany("company TWO");
        job2.setDesignation("designation TWO");
        job2.setId(2);
        job2.setStartDate(LocalDate.of(2022, 1,1));
        job2.setEndDate(LocalDate.of(2022, 3,17));
        job2.getResponsibilities().add("Other Resp");
        job2.getResponsibilities().add("Resp two");

        List<Job> jobs = new ArrayList<Job>();
        jobs.clear();
        jobs.add(job1); jobs.add(job2);
        profile1.setJobs(jobs);

        // profile1.setJobs(Arrays.asList(job1,job2));


        Education e1 = new Education();
        e1.setCollege("Osmania");
        e1.setCourse("Physics");
        e1.setStartDate(LocalDate.of(2022, 1,1));
        e1.setEndDate(LocalDate.of(2022, 3,17));

        Education e2 = new Education();
        e2.setCollege("Kakatiya");
        e2.setCourse("Maths");
        e2.setStartDate(LocalDate.of(2012, 1,1));
        e2.setEndDate(LocalDate.of(2015, 8,7));
        List<Education> eds = new ArrayList<>();
        eds.clear();
        eds.add(e1); eds.add(e2);

        profile1.setEducations(eds);
        
        userProfileRepository.save(profile1);
        System.out.println("jobs created for newton");
        
        return "Hello!!";

    }

    @GetMapping("/edit")
    public String edit(Model model, Principal principal){
        String userName = principal.getName();  //NOTE name gets you username. These are from security tables user or user_table. Real name will be part of profile

        Optional<UserProfile> profileOpt = userProfileRepository.findByUserName(userName);
        profileOpt.orElseThrow( () -> new RuntimeException("profile not found "+userName));

        UserProfile profile = profileOpt.get();

        model.addAttribute("userProfile", profile);  
        model.addAttribute("userId", userName);  
        System.out.println("userProfile loaded from DB and set to model attribute, so that its avbl in template for editing");
        return "profile-edit";

    }

    @PostMapping("/edit")
    public String save(Model model, Principal principal){
        String username = principal.getName();  

        return "redirect:/view/username";

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
