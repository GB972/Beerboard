package fr.almeri.beerboard.repositories;

import fr.almeri.beerboard.models.Biere;
import fr.almeri.beerboard.models.BiereId;
import fr.almeri.beerboard.models.Marque;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface BiereRepository extends CrudRepository<Biere, BiereId> {

    @Query("select COUNT(biere.version) from Biere biere, Marque marque WHERE biere.marque.nomMarque = marque.nomMarque group by marque.nomMarque ORDER BY marque.nomMarque ASC")
    public ArrayList<Integer> getNombreVersionParMarqueAsc();

    @Query("select biere.tauxAlcool from Biere biere group by biere.tauxAlcool ORDER BY biere.tauxAlcool ASC")
    public ArrayList<Integer> getListeDesTauxAsc();

    @Query("select COUNT(biere.version) from Biere biere group by biere.tauxAlcool ORDER BY biere.tauxAlcool ASC")
    public ArrayList<Integer> getNombreBieresParTauxAsc();

    @Query("SELECT biere FROM Biere biere WHERE  biere.marque.brasserie.codeBrasserie = :code ORDER BY biere.marque.nomMarque, biere.version ASC")
    public ArrayList<Biere> getListeVersionByMarque(@Param("code") String code);


    @Query(" select b from Biere b where b.marque.brasserie.codeBrasserie = :code ")
    public ArrayList<Biere> getListeMarquesVersions(@Param("code") String code);
}
