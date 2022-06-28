package fr.almeri.beerboard.controllers;

import fr.almeri.beerboard.models.User;
import fr.almeri.beerboard.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import static fr.almeri.beerboard.controllers.UserControlleur.getSalt;
import static fr.almeri.beerboard.controllers.UserControlleur.hashMD5withSalt;

@Controller
public class ProfileController {

    @Autowired
    private UserRepository userRepository;
    @GetMapping("/")
    public String connexion(Model pModel) {
//        String pass = "pass";
//        try
//
//        {
//            byte[] salt =  getSalt();
//            String hashPass = hashMD5withSalt(pass, salt);
//            User u = new User("Guillaume","Boutin","gboutin@beerboard.fr", hashPass, salt);
//            userRepository.save(u);
//        }
//        catch(NoSuchAlgorithmException e)
//
//        {
//            e.printStackTrace();
//        }
//        catch(
//                NoSuchProviderException e)
//
//        {
//            e.printStackTrace();
//        }
        return "login";
    }

    @PostMapping("/connect-user")
    public String connectUtilisateur(@ModelAttribute User user, RedirectAttributes redir, HttpSession session) {
        User userTest = userRepository.compteExistant(user.getLogin());
        if (userTest != null) {
            String hashmdp = hashMD5withSalt(user.getPassword(), userTest.getSalt());
            if (hashmdp.equals(userTest.getPassword())) {
                session.setAttribute("infoConnexion",userTest);
                return "redirect:/index";
            }
        }
        redir.addFlashAttribute("msg", "Le login et/ou le mot de passe est/sont erronés.");
        return "redirect:/";
    }

    @GetMapping("/motdepasse")
    public String motdepasse(Model pModel) {
        return "motdepasse";
    }

    @PostMapping("/mdp-user")
    public String mdpUtilisateur(@ModelAttribute User user, @RequestParam String emailUtilisateur, RedirectAttributes redir) {
        User userConnect = userRepository.compteExistant(emailUtilisateur);
        if (userConnect != null) {
            if (user.getLogin().equals(userConnect.getLogin())) {
                redir.addFlashAttribute("msg", "Un mail vous a été envoyé pour réinitialiser votre mot de passe.");
                return "redirect:/";
            }
        }
        redir.addFlashAttribute("msg", "Adresse email incorrect. Vérifiez votre saisie.");
        return "redirect:/motdepasse";
    }
    @GetMapping("/register")
    public String inscription(Model pModel) {
        return "register";
    }

    @PostMapping("/valid-user")
    public String addNouvelUtilisateur(@ModelAttribute User utilisateur, @RequestParam String emailUtilisateur, @RequestParam String inputPasswordConfirm, @RequestParam String mdpUtilisateur, Model pModel, RedirectAttributes redir) {
        if (inputPasswordConfirm.equals(mdpUtilisateur)) {
            if (userRepository.getUser(emailUtilisateur) != null) {
                redir.addFlashAttribute("msg", "Email déjà existant. Veuillez vous connecter en utilisant cet email.");
                return "redirect:/register";
            } else {
                userRepository.save(utilisateur);
                redir.addFlashAttribute("msg", "Le compte a bien été créé. Veuillez vous connecter.");
                return "redirect:/";
            }
        } else {
            redir.addFlashAttribute("msg", "Mots de passe incorrects. Veuillez recommencer.");
            return "redirect:/register";
        }
    }
}
