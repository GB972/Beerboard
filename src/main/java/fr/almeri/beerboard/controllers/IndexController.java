package fr.almeri.beerboard.controllers;

import fr.almeri.beerboard.models.User;
import fr.almeri.beerboard.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;




@Controller
public class IndexController {

    @Autowired
    private PaysRepository paysRepository;

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private BrasserieRepository brasserieRepository;

    @Autowired
    private MarqueRepository marqueRepository;

    @Autowired
    private BiereRepository biereRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String home(Model pModel, HttpSession pSession) {
        pModel.addAttribute("bieres", 328);
        pModel.addAttribute("brasseries", 99);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss");
        pModel.addAttribute("updated", dtf.format(LocalDateTime.now()));

        //pieChart
        ArrayList<String> labelsPieChart = regionRepository.getListeNomRegionAsc();
        pModel.addAttribute("labelsPieChart", labelsPieChart);
        ArrayList<Integer> datasPieChart = brasserieRepository.getListeNomBrasserieParRegionAsc();
        pModel.addAttribute("datasPieChart", datasPieChart);

        //AreaChart
        pModel.addAttribute("labelsAreaChart", biereRepository.getListeDesTauxAsc());
        pModel.addAttribute("datasAreaChart", biereRepository.getNombreBieresParTauxAsc());

        // Consommation
        ArrayList<String> labelsBarChart = paysRepository.getListeNomPaysAsc();
        pModel.addAttribute("labelsBarChart", labelsBarChart);
        ArrayList<Integer> datasConsommation = paysRepository.getListeConsommationPaysAsc();
        pModel.addAttribute("datasConsommation", datasConsommation);
        ArrayList<Integer> datasProduction = paysRepository.getListeProductionPaysAsc();
        pModel.addAttribute("datasProduction", datasProduction);

        pModel.addAttribute("labelsBarChart1", brasserieRepository.getListeNomBrasserieAsc());
        pModel.addAttribute("datasBarChart1", brasserieRepository.getNombreMarqueParBrasserieAsc());

        pModel.addAttribute("labelsBarChart2", marqueRepository.getListeMarque());
        pModel.addAttribute("datasBarChart2", biereRepository.getNombreVersionParMarqueAsc());

        return "index";
    }

    @GetMapping("/logout")
    public String logout(Model pModel, RedirectAttributes pRedirectAttributes, HttpSession pSession) {
        return "redirect:/";
    }


//    String pass = "pass";
//    try
//
//    {
//        byte[] salt =  getSalt();
//        String hashPass = hashMD5withSalt(pass, salt);
//        User u = new User("gboutin@beerboard.fr", hashPass, salt);
//        userRepository.save(u);
//    }
//    catch(NoSuchAlgorithmException e)
//
//    {
//        e.printStackTrace();
//    }
//    catch(
//    NoSuchProviderException e)
//
//    {
//        e.printStackTrace();
//    }
}
