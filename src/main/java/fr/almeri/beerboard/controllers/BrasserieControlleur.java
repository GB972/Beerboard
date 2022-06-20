package fr.almeri.beerboard.controllers;

import fr.almeri.beerboard.models.Brasserie;
import fr.almeri.beerboard.repositories.BrasserieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class BrasserieControlleur {

    @Autowired
    private BrasserieRepository brasserieRepository;

    @GetMapping("/brasserie")
    public String getListBrasserie(Model pModel){

        ArrayList<Brasserie> listBrasserieFromDataBase = (ArrayList<Brasserie>)brasserieRepository.findAll();
        pModel.addAttribute("listBrasserie",listBrasserieFromDataBase);

        return  "brasserie";
    }
}
