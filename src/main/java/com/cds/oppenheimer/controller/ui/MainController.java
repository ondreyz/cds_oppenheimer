package com.cds.oppenheimer.controller.ui;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    /**
     * Redirects to relevant page based on authentication status and role
     * @param user
     * @return
     */
    @GetMapping("/")
    public ModelAndView redirectToMainPage(@AuthenticationPrincipal User user) {
        String redirectPath = null;
        if (user != null) {
           redirectPath = "/home";
        } else {
            redirectPath = "/login";
        }
        return new ModelAndView("redirect:" + redirectPath);
    }

    @GetMapping("/login")
    public ModelAndView loadLoginPage(@AuthenticationPrincipal User user) {
        if (user != null) {
            return new ModelAndView("redirect:/home");
        }
        return new ModelAndView("login");
    }

    @GetMapping("/home")
    public ModelAndView loadHomePage(@AuthenticationPrincipal User user) {
        ModelAndView modelAndView = new ModelAndView("home");

        if (user != null) {
            Collection<GrantedAuthority> userAuthorities = user.getAuthorities();
            for (GrantedAuthority authority : userAuthorities) {
                String authorityRole = authority.getAuthority();
                // Spring security prefixes roles with "ROLE_"
                if (authorityRole.equals("ROLE_CLERK")) {
                    modelAndView.addObject("isClerk", true);
                } else if (authorityRole.equals("ROLE_BOOKKEEPER")) {
                    modelAndView.addObject("isBookkeeper", true);
                } else if (authorityRole.equals("ROLE_GOVERNOR")) {
                    modelAndView.addObject("isGovernor", true);
                }
            }
        }

        return modelAndView;
    }
}