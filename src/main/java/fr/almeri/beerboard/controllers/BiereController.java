/**
 * Projet : Mise en place d'une plateforme de gestion (Brasserie)
 * Gestion des bières (consultation, modification et ajout)
 *
 * @date : 21/06/2022
 * @class : BiereController
 * @author: Zankidine Abdou
 */

package fr.almeri.beerboard.controllers;

import fr.almeri.beerboard.models.*;
import fr.almeri.beerboard.repositories.BiereRepository;
import fr.almeri.beerboard.repositories.MarqueRepository;
import fr.almeri.beerboard.repositories.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class BiereController {
    @Autowired
    private BiereRepository biereRepository;

    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private MarqueRepository marqueRepository;


    @GetMapping("/beers")
    public String listeBieres(Model model, HttpSession session) {
        ArrayList<Biere> listBieresFromDatabase = (ArrayList<Biere>) biereRepository.findAll();
        model.addAttribute("listBieres", listBieresFromDatabase);
        if (session.getAttribute("infoConnexion") != null) {
            return "beers";
        }
        return "redirect:/";
    }


    @GetMapping("/see-beer/{marque}/{version}")
    public String detailBiere(Model model, @PathVariable String marque, @PathVariable String version, HttpSession session) {
        BiereId idBiere = new BiereId(new Marque(marque), version);
        Biere biere = biereRepository.findById(idBiere).orElseThrow();
        model.addAttribute("biere", biere);
        if (session.getAttribute("infoConnexion") != null) {
            return "see-beer";
        }
        return "redirect:/";

    }


    @GetMapping("/add-beer")
    public String ajouterBiereForm(Model model, HttpSession session) {
        ArrayList<Marque> listMarque = (ArrayList<Marque>) marqueRepository.findAll();
        model.addAttribute("listMarque", listMarque);
        ArrayList<Type> listType = (ArrayList<Type>) typeRepository.findAll();
        model.addAttribute("listType", listType);
        if (session.getAttribute("infoConnexion") != null) {
            return "add-beer";
        }
        return "redirect:/";
    }


    @GetMapping("/update-beer/{marque}/{version}")
    public String modifierBrasserieForm(Model model, @PathVariable String marque, @PathVariable String version, HttpSession session) {

        BiereId idBiere = new BiereId(new Marque(marque), version);
        model.addAttribute("biere", biereRepository.findById(idBiere).orElseThrow());
        if (session.getAttribute("infoConnexion") != null) {
            return "modify-beer";
        }
        return "redirect:/";
    }

    @PostMapping("/valid-beer")
    public String addNouvelleBiere(@ModelAttribute Biere biere, HttpSession session, RedirectAttributes redir) {
        BiereId id = new BiereId(biere.getMarque(), biere.getVersion());
        if (session.getAttribute("infoConnexion") != null) {
            if (!biereRepository.existsById(id)) {
                biereRepository.save(biere);
                return "redirect:/beers";
            } else {
                redir.addFlashAttribute("msg", "Une bière de cette marque avec cette version existe déjà, veuillez saisir une nouvelle version.");
                return "redirect:/add-beer";
            }
        }
        return "redirect:/";
    }

    @PostMapping("/update-beer")
    public String updateBiere(@ModelAttribute Biere biere, HttpSession session) {
        BiereId id = new BiereId(biere.getMarque(), biere.getVersion());
        Biere oldBiere = biereRepository.findById(id).orElseThrow();
        biere.setType(oldBiere.getType());
        biereRepository.save(biere);
        if (session.getAttribute("infoConnexion") != null) {
            return "redirect:/beers";
        }
        return "redirect:/";
    }

    @PostMapping("/drop-beer")
    public String deleteBiere(@ModelAttribute Biere biere, HttpSession session) {
        biereRepository.deleteById(new BiereId(biere.getMarque(), biere.getVersion()));
        if (session.getAttribute("infoConnexion") != null) {
            return "redirect:/beers";
        }
        return "redirect:/";
    }

    @GetMapping("/delete-beer")
    public String getFicheBiereSuppression(Model pModel, HttpSession session, @RequestParam(required = true) String marque, @RequestParam(required = true) String version) {
        BiereId biereId = new BiereId(new Marque(marque), version);
        pModel.addAttribute("biere", biereRepository.findById(biereId).orElseThrow());
        if (session.getAttribute("infoConnexion") != null) {
            return "delete-beer";
        }
        return "redirect:/";
    }


}
