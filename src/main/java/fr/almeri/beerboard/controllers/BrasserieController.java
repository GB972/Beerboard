/**
 * Projet : Mise en place d'une plateforme de gestion (Brasserie)
 * Gestion des brasseries (consultation, modification et ajout)
 * @date : 21/06/2022
 * @class : BrasserieController
 * @author: Zankidine Abdou
 */
package fr.almeri.beerboard.controllers;

import fr.almeri.beerboard.models.Biere;
import fr.almeri.beerboard.models.Brasserie;
import fr.almeri.beerboard.repositories.BiereRepository;
import fr.almeri.beerboard.repositories.BrasserieRepository;
import fr.almeri.beerboard.repositories.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class BrasserieController {

    @Autowired
    private BrasserieRepository brasserieRepository;

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private BiereRepository biereRepository;

    /**
     * Affiche la liste des brasseries
     * Tableau
     * @param model
     * @return
     */
    @GetMapping("/breweries")
    public String listeBrasserie(Model model)
    {
        // On récupère l'ensemble des brasseries de la base de données
        ArrayList<Brasserie> listBrasserieFromDatabase = (ArrayList<Brasserie>) brasserieRepository.findAll();
        model.addAttribute("listBrasserie", listBrasserieFromDatabase);

        return "brasserie/index";
    }

    /**
     * Affiche la fiche détail d'une brasserie
     * Ainsi que la liste des marques et Versions concernant une bière
     * @param model
     * @param code
     * @return
     */
    @GetMapping("/see-brewery/{code}")
    public String detailBrasserie(Model model, @PathVariable String code)
    {
        // On récupère la brasserie pour la consultation
        Brasserie brasserie = brasserieRepository.findById(code).orElseThrow();
        model.addAttribute("brasserie", brasserie);

        // On récupère la lsite des marques et versions associées à une brasserie
        ArrayList<Biere> bieres = biereRepository.getListeVersionByMarque(code);
        model.addAttribute("bieres", bieres);

        return "brasserie/detail";

    }

    /**
     * Affiche le formulaire vierge pour l'ajout d'une brasserie
     * @param model
     * @return
     */
    @GetMapping("/add-brewery")
    public String ajouterBrasserieForm(Model model)
    {
        model.addAttribute("update", false);
        model.addAttribute("brasserie", new Brasserie());
        // regionRepository.getListeNomRegionObjAsc() retourne la liste des régions
        model.addAttribute("listeRegion", regionRepository.getListeNomRegionObjAsc());

        return "brasserie/ajouter";
    }

    /**
     * Traitement des données formulaire ajout/modification
     * @param brasserie
     * @param model
     * @return
     */
    @PostMapping("/add-brewery")
    public String ajouterBrasserie (@Validated @ModelAttribute Brasserie brasserie, Model model)
    {
        // Création d'une brasserie + enregistrement dans la base de données.
        brasserieRepository.save(brasserie);

        return "redirect:/breweries";
    }

    /**
     * Affiche le formulaire en modification en incluant
     * les données d'une brasserie
     * @param model
     * @param code
     * @return
     */
    @GetMapping("/update-brewery/{code}")
    public String modifierBrasserieForm(Model model,@PathVariable String code)
    {
        model.addAttribute("update", true);
        model.addAttribute("brasserie", brasserieRepository.findById(code));
        model.addAttribute("listeRegion", regionRepository.getListeNomRegionObjAsc());

        return "brasserie/ajouter";
    }
}
