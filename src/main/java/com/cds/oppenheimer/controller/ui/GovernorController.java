package com.cds.oppenheimer.controller.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/governor")
public class GovernorController {

    @PostMapping("/dispensetaxrelief")
    public ModelAndView postMethodName() {
        return new ModelAndView("redirect:/governor/taxreliefdispensed");
    }
}