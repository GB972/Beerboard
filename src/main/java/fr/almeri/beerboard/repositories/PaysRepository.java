package fr.almeri.beerboard.repositories;

import fr.almeri.beerboard.models.Pays;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

//@Repository indique que c'est dans cette interface qu'on effectue les requetes en bdd
@Repository
public interface PaysRepository extends CrudRepository<Pays,String> {

    // select "classique" mais obligation de définir un alias pour la table
    @Query("SELECT p.nomPays from Pays p order by p.nomPays asc")
    public ArrayList<String> getNomPaysAsc();

    @Query("SELECT p.consommation from Pays p order by p.nomPays asc")
    public ArrayList<Double> getConsoPaysAsc();

    @Query("SELECT p.production from Pays p order by p.nomPays asc")
    public ArrayList<Double> getProdPaysAsc();

}
