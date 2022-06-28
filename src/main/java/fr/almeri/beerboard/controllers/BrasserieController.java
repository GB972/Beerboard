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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Controller
public class BrasserieController {

    @Autowired
    private BrasserieRepository brasserieRepository;

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private BiereRepository biereRepository;

    @GetMapping("/breweries")
    public String listeBrasserie(Model model)
    {
        // On récupère l'ensemble des brasseries de la base de données
        ArrayList<Brasserie> listBrasserieFromDatabase = (ArrayList<Brasserie>) brasserieRepository.findAll();
        model.addAttribute("listBrasserie", listBrasserieFromDatabase);

        return "brasserie/index";
    }

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

    @GetMapping("/add-brewery")
    public String ajouterBrasserieForm(Model model)
    {
        model.addAttribute("update", false);
        model.addAttribute("brasserie", new Brasserie());
        model.addAttribute("listeRegion", regionRepository.getListeNomRegionObjAsc());

        return "brasserie/ajouter";
    }

    @PostMapping("/add-brewery")
    public String ajouterBrasserie (@Validated @ModelAttribute Brasserie brasserie, Model model)
    {
        // Création d'une brasserie + enregistrement dans la base de données.
        brasserieRepository.save(brasserie);

        return "redirect:/breweries";
    }

    @GetMapping("/update-brewery/{code}")
    public String modifierBrasserieForm(Model model,@PathVariable String code)
    {
        model.addAttribute("update", true);
        model.addAttribute("brasserie", brasserieRepository.findById(code));
        model.addAttribute("listeRegion", regionRepository.getListeNomRegionObjAsc());

        return "brasserie/ajouter";
    }

    @PostMapping("/valid-brewery")
    public String addNouvelleBrasserie(@ModelAttribute Brasserie brasserie, RedirectAttributes redir){
        if (!brasserieRepository.existsById(brasserie.getCodeBrasserie())){
            brasserieRepository.save(brasserie);
            return "redirect:/breweries";
        } else{
            redir.addFlashAttribute("msg", "L'identifiant de la brasserie existe déjà, veuillez en saisir un nouveau ou vérifier que cette brasserie n'existe pas déjà.");
            return "redirect:/add-brewery";
        }
    }

    @PostMapping("/update-brewery")
    public String updateBrasserie(@ModelAttribute Brasserie brasserie){
        brasserieRepository.save(brasserie);
        return "redirect:/breweries";
    }

    @PostMapping("/drop-brewery")
    public String deleteBrasserie(@ModelAttribute Brasserie brasserie, Model pModel, RedirectAttributes redir){

        ArrayList<Biere> bieres = biereRepository.getListeMarquesVersions(brasserie.getCodeBrasserie());
        pModel.addAttribute("bieres", bieres);

        boolean existance = bieres.isEmpty();

        if (existance) {
            brasserieRepository.deleteById(brasserie.getCodeBrasserie());
            return "redirect:/breweries";
        } else {
            redir.addFlashAttribute("msg","La brasserie ne peut être supprimée car des bières lui sont encore associées.");
            return "redirect:/delete-brewery/" + brasserie.getCodeBrasserie();
        }
    }
}
