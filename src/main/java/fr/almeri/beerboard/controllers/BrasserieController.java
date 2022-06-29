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
import fr.almeri.beerboard.models.Region;
import fr.almeri.beerboard.repositories.BiereRepository;
import fr.almeri.beerboard.repositories.BrasserieRepository;
import fr.almeri.beerboard.repositories.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
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
    public String listeBrasserie(Model model, HttpSession session)
    {
        // On récupère l'ensemble des brasseries de la base de données
        ArrayList<Brasserie> listBrasserieFromDatabase = (ArrayList<Brasserie>) brasserieRepository.findAll();
        model.addAttribute("listBrasserie", listBrasserieFromDatabase);

        if (session.getAttribute("infoConnexion") != null) {
            return "breweries";
        }
        return "redirect:/";
    }

    @GetMapping("/see-brewery/{code}")
    public String detailBrasserie(Model model, @PathVariable String code, HttpSession session)
    {
        // On récupère la brasserie pour la consultation
        Brasserie brasserie = brasserieRepository.findById(code).orElseThrow();
        model.addAttribute("brasserie", brasserie);

        // On récupère la lsite des marques et versions associées à une brasserie
        ArrayList<Biere> bieres = biereRepository.getListeVersionByMarque(code);
        model.addAttribute("bieres", bieres);

        if (session.getAttribute("infoConnexion") != null) {

            return "brewery";
        }

        return "redirect:/";

    }

    @GetMapping("/ajouterBrewery")
    public String ajouterBrasserieForm(Model model, HttpSession session)
    {
        ArrayList<Region> ListRegion = (ArrayList<Region>) regionRepository.findAll();
        model.addAttribute("listRegion", ListRegion);
        model.addAttribute("update", false);
        model.addAttribute("brasserie", new Brasserie());
        if (session.getAttribute("infoConnexion") != null) {
            return "ajouterBrewery";
        }
        return "redirect:/";
    }
    @PostMapping("/ajouterBrewery/{nomBrasserie}")
    public String ajouterBrasserieForm(Model model, @PathVariable(required = false) String nomBrasserie, HttpSession session){
        ArrayList<Region> ListRegion = (ArrayList<Region>) regionRepository.findAll();
        model.addAttribute("listRegion", ListRegion);
        model.addAttribute("update", false);
        Brasserie brasserie = new Brasserie();
        model.addAttribute("brasserie", brasserieRepository.findById(brasserie.getCodeBrasserie()).orElseThrow());
        if (session.getAttribute("infoConnexion") != null) {
            return "ajouterBrewery";
        }
        return "redirect:/";
    }

    @GetMapping("/update-brewery/{code}")
    public String modifierBrasserieForm(Model model,@PathVariable String code, HttpSession session)
    {
        ArrayList<Region> ListRegion = (ArrayList<Region>) regionRepository.findAll();
        model.addAttribute("listRegion", ListRegion);
        model.addAttribute("update", true);
        model.addAttribute("brasserie", brasserieRepository.findById(code));
        model.addAttribute("listeRegion", regionRepository.getListeNomRegionObjAsc());
        if (session.getAttribute("infoConnexion") != null) {
            return "ajouterBrewery";
        }
        return "redirect:/";
    }

    @PostMapping("/update-brewery/{nomBrasserie}")
    public String modifierBrasserieForm(Model model, HttpSession session){
        ArrayList<Region> ListRegion = (ArrayList<Region>) regionRepository.findAll();
        model.addAttribute("listRegion", ListRegion);
        model.addAttribute("update", true);
        Brasserie brasserie = new Brasserie();
        model.addAttribute("brasserie", brasserieRepository.findById(brasserie.getCodeBrasserie()).orElseThrow());
        if (session.getAttribute("infoConnexion") != null) {
            return "redirect:/breweries";
        }
        return "redirect:/";
    }

    @PostMapping("/valid-brewery")
    public String addNouvelleBrasserie(@ModelAttribute Brasserie brasserie, RedirectAttributes redir, HttpSession session,String update){
        if (session.getAttribute("infoConnexion") != null) {
            if("false".equals(update)) {
                if (!brasserieRepository.existsById(brasserie.getCodeBrasserie())) {
                    brasserieRepository.save(brasserie);
                    return "redirect:/breweries";
                } else {
                    redir.addFlashAttribute("msg", "L'identifiant de la brasserie existe déjà, veuillez en saisir un nouveau ou vérifier que cette brasserie n'existe pas déjà.");
                    return "redirect:/ajouterBrewery";
                }
            }else{
                brasserieRepository.save(brasserie);
                return "redirect:/breweries";
            }
        }
        return "redirect:/";
    }



    @GetMapping("/drop-brewery/{brasserie}")
    public String deleteBrasserie(@PathVariable String brasserie, Model pModel, RedirectAttributes redir){

        ArrayList<Biere> bieres = biereRepository.getListeMarquesVersions(brasserie);
        pModel.addAttribute("bieres", bieres);

        boolean existance = bieres.isEmpty();

        if (existance) {
            brasserieRepository.deleteById(brasserie);
            return "redirect:/breweries";
        } else {
            redir.addFlashAttribute("msg", "La brasserie possède des bières donc ne peut être supprimer");
            return "redirect:/breweries" ;
        }
    }
}
