package fr.almeri.beerboard.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class ProfileController {

    @GetMapping("/profil")
    public String profile(Model pModel){
        /*TODO*/
        return "";
    }
}
