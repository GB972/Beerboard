package fr.almeri.beerboard.repositories;

import fr.almeri.beerboard.models.Marque;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;

@Repository
public interface MarqueRepository extends CrudRepository<Marque, String> {

    @Query("SELECT marque FROM Marque marque WHERE marque.brasserie.codeBrasserie = :code")
    public ArrayList<Marque> getListeMarqueByBrasserie(@Param("code") String code);

    @Query("SELECT marque FROM Marque marque ORDER BY marque.nomMarque ASC")
    public ArrayList<Marque> getListeMarque();
}
