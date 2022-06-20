package fr.almeri.beerboard.controllers;

import fr.almeri.beerboard.models.*;
import fr.almeri.beerboard.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Scanner;

//Permet d'indiquer à Spring Boot qu'il y aura un "routage" dans ce fichier
@Controller
public class ExempleControlleur {

    //@Autowired permet d'instancier automatiquement paysRepository si besoin
    @Autowired
    private PaysRepository paysRepository;

    @Autowired
    private BrasserieRepository brasserieRepository;

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private MarqueRepository marqueRepository;

    @Autowired
    private BiereRepository biereRepository;

    //permet de definir une route appelée avec la méthode GET("directement via l'URL")
    // Ici localhost:8888/example
    @GetMapping("/example")
    public String getPageExemple(Model pModel) {
        //L'objet Model est inctancié lorsqu'on appelle la méthode getPageExample par Spring
        // il permet d'envoyer des données dynamique à la page
        // La méthode addAttribute prend 2 paramètres
        // 1er : nom de la variable à utiliser dans l'html
        // 2eme: données à afficher
        pModel.addAttribute("prenom", "Marie");

        Pays pays0 = new Pays();
        pays0.setNomPays("Sealand");
        pays0.setConsommation(287.0);
        pays0.setProduction(1.0);
        Pays pays1 = new Pays();
        pays1.setNomPays("France");
        pays1.setConsommation(4478.0);
        pays1.setProduction(666.666);
        Pays pays2 = new Pays();
        pays2.setNomPays("Espagne");
        pays2.setConsommation(2541.3);
        pays2.setProduction(451.3);
        Pays pays3 = new Pays();
        pays3.setNomPays("Italie");
        pays3.setConsommation(4453.2);
        pays3.setProduction(663.5);
        Pays pays4 = new Pays();
        pays4.setNomPays("Suisse");
        pays4.setConsommation(784.2);
        pays4.setProduction(114.0);
        Pays pays5 = new Pays();
        pays5.setNomPays("Belgique");
        pays5.setConsommation(996.4);
        pays5.setProduction(44.1);
//
        ArrayList<Pays> listPays= new ArrayList<>();
        listPays.add(pays0);
        listPays.add(pays1);
        listPays.add(pays2);
        listPays.add(pays3);
        listPays.add(pays4);
        listPays.add(pays5);


        pModel.addAttribute("pays",pays0);


        ArrayList<Pays> listPaysFromDataBase = (ArrayList<Pays>)paysRepository.findAll();
        pModel.addAttribute("listPays",listPaysFromDataBase);

        ArrayList<Region> listRegionFromDataBase = (ArrayList<Region>) regionRepository.findAll();
        pModel.addAttribute("listRegion",listRegionFromDataBase);


        ArrayList<Marque> listMarqueFromDataBase = (ArrayList<Marque>)marqueRepository.findAll();
        pModel.addAttribute("listMarque",listMarqueFromDataBase);

        ArrayList<Type> listTypeFromDataBase = (ArrayList<Type>)typeRepository.findAll();
        pModel.addAttribute("listType",listTypeFromDataBase);

        ArrayList<Biere>  bieres = (ArrayList<Biere>)biereRepository.findAll();
        System.out.println();

        //Méthode permettant d'indiquer quelle page HTML on renvoie
        // ici example.html dans le repertoire template
        return "example";
    }
}
