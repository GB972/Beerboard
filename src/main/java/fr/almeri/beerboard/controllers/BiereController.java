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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String listeBieres(Model model) {
        // On récupère l'ensemble des bières de la base de données
        ArrayList<Biere> listBieresFromDatabase = (ArrayList<Biere>) biereRepository.findAll();

        model.addAttribute("listBieres", listBieresFromDatabase);

        return "bieres/index";
    }


    @GetMapping("/see-beer/{marque}/{version}")
    public String detailBiere(Model model, @PathVariable String marque, @PathVariable String version) {
        // Id bière
        BiereId idBiere = new BiereId(new Marque(marque), version);

        // Obj Bière
        Biere biere = biereRepository.findById(idBiere).orElseThrow();
        model.addAttribute("biere", biere);

        return "bieres/detail";

    }


    @GetMapping("/add-beer")
    public String ajouterBiereForm(Model model) {
        model.addAttribute("update", false);
        model.addAttribute("biere", new Biere());
        model.addAttribute("listeType", typeRepository.findAll());
        model.addAttribute("listeMarque", marqueRepository.findAll());

        return "bieres/ajouter";
    }


    @PostMapping("/add-beer")
    public String ajouterBiere(@Validated @ModelAttribute Biere biere, Model model) {


        biereRepository.save(biere);

        return "redirect:/beers";
    }


    @GetMapping("/update-beer/{marque}/{version}")
    public String modifierBrasserieForm(Model model, @PathVariable String marque, @PathVariable String version) {

        BiereId idBiere = new BiereId(new Marque(marque), version);

        model.addAttribute("update", true);
        model.addAttribute("biere", biereRepository.findById(idBiere));
        model.addAttribute("listeType", typeRepository.findAll());
        model.addAttribute("listeMarque", marqueRepository.findAll());

        return "bieres/ajouter";
    }

    @PostMapping("/valid-beer")
    public String addNouvelleBiere(@ModelAttribute Biere biere, RedirectAttributes redir) {
        BiereId id = new BiereId(biere.getMarque(), biere.getVersion());
        if (!biereRepository.existsById(id)) {
            biereRepository.save(biere);
            return "redirect:/beers";
        } else {
            redir.addFlashAttribute("msg", "Une bière de cette marque avec cette version existe déjà, veuillez saisir une nouvelle version.");
            return "redirect:/add-beer";
        }
    }

    @PostMapping("/update-beer")
    public String updateBiere(@ModelAttribute Biere biere) {
        BiereId id = new BiereId(biere.getMarque(), biere.getVersion());
        Biere oldBiere = biereRepository.findById(id).orElseThrow();
        biere.setType(oldBiere.getType());
        biereRepository.save(biere);
        return "redirect:/beers";
    }

    @PostMapping("/drop-beer")
    public String deleteBiere(@ModelAttribute Biere biere) {
        biereRepository.deleteById(new BiereId(biere.getMarque(), biere.getVersion()));
        return "redirect:/beers";
    }


}
