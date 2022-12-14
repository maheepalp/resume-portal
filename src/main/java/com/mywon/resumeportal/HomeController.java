package com.mywon.resumeportal;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mywon.resumeportal.models.UserProfile;

@Controller
public class HomeController {

    @Autowired
    UserProfileRepository userProfileRepository;

    @GetMapping("/")
    public String home(){
        return "Hello!!";

    }

    @GetMapping("/edit")
    public String edit(){
        return "Edit!!";

    }

    @GetMapping("/view/{userId}")
    public String view(@PathVariable("userId") String aaa, Model model){
        Optional<UserProfile> profile = userProfileRepository.findByUserName(aaa);
        profile.orElseThrow( () -> new RuntimeException("profile not found "+aaa));

        model.addAttribute("userId", aaa);
        model.addAttribute("userProfile", profile.get());
        
        return "./"+profile.get().getTheme()+"/index";

    }
}
