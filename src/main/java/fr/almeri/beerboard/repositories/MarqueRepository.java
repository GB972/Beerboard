package fr.almeri.beerboard.repositories;

import fr.almeri.beerboard.models.Marque;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface MarqueRepository extends CrudRepository<Marque, String> {

    @Query("SELECT count(m.nomMarque) from Marque m group by m.brasserie.codeBrasserie order by  m.brasserie.codeBrasserie")
    public ArrayList<Integer> getNbMarque();

}
