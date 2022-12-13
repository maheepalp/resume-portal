package com.mywon.resumeportal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

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
        model.addAttribute("userId", aaa);
        return "./1/index";

    }
}
