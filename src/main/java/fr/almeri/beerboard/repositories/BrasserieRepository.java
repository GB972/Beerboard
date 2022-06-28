package fr.almeri.beerboard.repositories;

import fr.almeri.beerboard.models.Brasserie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface BrasserieRepository extends CrudRepository<Brasserie, String> {

    @Query("select COUNT(brasserie.codeBrasserie) from Brasserie brasserie, Region region WHERE brasserie.region.nomRegion = region.nomRegion group by region.nomRegion ORDER BY region.nomRegion ASC")
    public ArrayList<Integer> getListeNomBrasserieParRegionAsc();

    @Query("select brasserie.nomBrasserie from Brasserie brasserie  ORDER BY brasserie.codeBrasserie ASC")
    public ArrayList<String> getListeNomBrasserieAsc();

    @Query("select COUNT(marque.brasserie.codeBrasserie) from Brasserie brasserie, Marque marque WHERE brasserie.codeBrasserie = marque.brasserie.codeBrasserie group by marque.brasserie.codeBrasserie ORDER BY marque.brasserie.codeBrasserie ASC")
    public ArrayList<Integer> getNombreMarqueParBrasserieAsc();



}
