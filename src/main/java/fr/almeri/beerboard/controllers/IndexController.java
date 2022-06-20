package fr.almeri.beerboard.controllers;

import fr.almeri.beerboard.repositories.*;
import org.attoparser.dom.INestableNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Controller
public class IndexController {

    @Autowired
    private PaysRepository paysRepository;

    @Autowired
    private BiereRepository biereRepository;

    @Autowired
    private MarqueRepository marqueRepository;

    @Autowired
    private BrasserieRepository brasserieRepository;

    @Autowired
    private RegionRepository regionRepository;

    @GetMapping("/")
    public String home(Model pModel, HttpSession pSession) {

        //carre bleu
        int bieres = biereRepository.getNbBieres();
        pModel.addAttribute("bieres", biereRepository.getNbBieres());
        //carre jaune
        int brasseries = brasserieRepository.getNbBrasserie();
        pModel.addAttribute("brasseries", brasserieRepository.getNbBrasserie());

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss");
        pModel.addAttribute("updated", dtf.format(LocalDateTime.now()));

        //brasserie par région
        List<String> labelsPieChart = brasserieRepository.getLabelsNomRegion() ;
        ArrayList<Integer>  datasPieChart = brasserieRepository.getNbBrasserieRegion();
        pModel.addAttribute("labelsPieChart", labelsPieChart);
        pModel.addAttribute("datasPieChart", datasPieChart);

        //Nombre de bières par taux d'alcool
        ArrayList<Integer> labelsAreaChart = biereRepository.getNbBiereTaux();
        ArrayList<Integer> datasAreaChart = biereRepository.getNbTauxBiere();
        pModel.addAttribute("labelsAreaChart",labelsAreaChart );
        pModel.addAttribute("datasAreaChart",datasAreaChart);


        //Consommation & production en bières des pays
        ArrayList<String> labelsBarChart = paysRepository.getNomPaysAsc();
        ArrayList<Double> datasConsommation = paysRepository.getConsoPaysAsc();
        ArrayList<Double> datasProduction = paysRepository.getProdPaysAsc();
        pModel.addAttribute("labelsBarChart", labelsBarChart);
        pModel.addAttribute("datasConsommation", datasConsommation);
        pModel.addAttribute("datasProduction", datasProduction);

        //Nombre de marques référencés par brasseries
        ArrayList<String> labelsBarChart1 = brasserieRepository.getBrasserie();
        ArrayList<Integer> datasBarChart1 = marqueRepository.getNbMarque();
        pModel.addAttribute("labelsBarChart1",brasserieRepository.getBrasserie());
        pModel.addAttribute("datasBarChart1", marqueRepository.getNbMarque());

        // nombre de versions par marque
        ArrayList<String> labelsBarChart2 = biereRepository.getNomMarque();
        ArrayList<Integer> datasBarChart2= biereRepository.getNbVersionParMarque();
        pModel.addAttribute("labelsBarChart2", biereRepository.getNomMarque());
        pModel.addAttribute("datasBarChart2", biereRepository.getNbVersionParMarque());

        return "index";
    }

    @GetMapping("/logout")
    public String logout(Model pModel, RedirectAttributes pRedirectAttributes, HttpSession pSession) {
        return "redirect:/";
    }
}
