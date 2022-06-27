package fr.almeri.beerboard.controllers;


import fr.almeri.beerboard.models.*;
import fr.almeri.beerboard.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;


@Controller
public class BaseController {

    @Autowired
    private PaysRepository paysRepository;

    @Autowired
    private BrasserieRepository brasserieRepository;

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private MarqueRepository marqueRepository;

    @Autowired
    private TypeRepository typeRepository;
    @GetMapping("/example")
    public String example(Model model)
    {

        ArrayList<Pays> listPaysFromDatabase = (ArrayList<Pays>) paysRepository.findAll();
        model.addAttribute("listPays", listPaysFromDatabase);

        ArrayList<Brasserie> listBrasserieFromDatabase = (ArrayList<Brasserie>) brasserieRepository.findAll();
        model.addAttribute("listBrasserie", listBrasserieFromDatabase);

        ArrayList<Region> listRegionFromDatabase = (ArrayList<Region>) regionRepository.findAll();
        model.addAttribute("listRegion", listRegionFromDatabase);

        ArrayList<Marque> listMarqueFromDatabase = (ArrayList<Marque>) marqueRepository.findAll();
        model.addAttribute("listMarque", listMarqueFromDatabase);

        ArrayList<Type> listTypeFromDatabase = (ArrayList<Type>) typeRepository.findAll();
        model.addAttribute("listType", listTypeFromDatabase);

        return "hello";
    }
}
