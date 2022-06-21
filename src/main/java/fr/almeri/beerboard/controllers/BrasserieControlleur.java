package fr.almeri.beerboard.controllers;

import fr.almeri.beerboard.models.Biere;
import fr.almeri.beerboard.models.Brasserie;
import fr.almeri.beerboard.repositories.BiereRepository;
import fr.almeri.beerboard.repositories.BrasserieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class BrasserieControlleur {

    @Autowired
    private BrasserieRepository brasserieRepository;

    @Autowired
    private BiereRepository biereRepository;

    @GetMapping("/breweries")
    public String getListBrasserie(Model pModel){

        ArrayList<Brasserie> listBrasserieFromDataBase = (ArrayList<Brasserie>)brasserieRepository.findAll();
        pModel.addAttribute("listBrasserie",listBrasserieFromDataBase);

        return  "breweries";
    }

    @GetMapping("/see-brewery/{code}")
    public String getBrewery(Model pModel, @PathVariable(required = true) String code){
        //le nom de la variable choisi entre { } doit être le même dans les paramètres !!

        //Je recupère la brasserie via le repository avec findById
        // et je passe en paramêtre ce que je récupère via l'URL
        // orElse... va déclencher une exception si on ne trouve pas la brasserie & non arreter le programme

        Brasserie brasserie = brasserieRepository.findById(code).orElseThrow();
        pModel.addAttribute("brasserie", brasserie);

        ArrayList<Biere> listBiereBrasserie = biereRepository.getBiereBrasserie(code);
        pModel.addAttribute("biere", listBiereBrasserie);
        return "brewery";
    }

//    //URL appelée : /see-brewery1?code=variable
//    @GetMapping("/see-brewery1")
//    public String getBreweryByCode(Model pModel, @RequestParam(required = true) String code, @RequestParam(required = false)String toto){
//        // le nom donné au paramètre dans le @Requeteparam doit être le même que la clé utilisée dans l'url
//
//        ArrayList<Biere> listBiereBrasserie = biereRepository.getBiereBrasserie(code);
//        pModel.addAttribute("biere", listBiereBrasserie);
//        return "brewery";
//    }
}
