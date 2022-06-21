package fr.almeri.beerboard.controllers;

import fr.almeri.beerboard.models.Biere;
import fr.almeri.beerboard.models.BiereId$;
import fr.almeri.beerboard.models.Brasserie;
import fr.almeri.beerboard.repositories.BiereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;


@Controller
public class BiereControlleur {

    @Autowired
    private BiereRepository biereRepository;

    @GetMapping("/beers")
    public String getListBeers(Model pModel){
        ArrayList<Biere> listBrasserieFromDataBase = (ArrayList<Biere>) biereRepository.findAll();
        pModel.addAttribute("listBeers",listBrasserieFromDataBase);

        return "beers";
    }

    @GetMapping("/see-beer/{code}")
    public String getBeer(Model pModel, @PathVariable(required = true) BiereId$ code){
        //le nom de la variable choisi entre { } doit être le même dans les paramètres !!

        //Je recupère la brasserie via le repository avec findById
        // et je passe en paramêtre ce que je récupère via l'URL
        // orElse... va déclencher une exception si on ne trouve pas la brasserie & non arreter le programme

        Biere biere = biereRepository.findById(code).orElseThrow();
        return "beer";
    }

}
