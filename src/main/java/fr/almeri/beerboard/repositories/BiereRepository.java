package fr.almeri.beerboard.repositories;

import fr.almeri.beerboard.models.Biere;
import fr.almeri.beerboard.models.BiereId$;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface BiereRepository extends CrudRepository<Biere, BiereId$> {

    @Query("select count(b.marque) from Biere as b")
    public int getNbBieres();

    @Query("select distinct count(b.marque.nomMarque), b.tauxAlcool from Biere as b group by b.tauxAlcool")
    public ArrayList<Integer> getNbBiereTaux();

    @Query("select distinct b.tauxAlcool from Biere as b group by b.tauxAlcool")
    public ArrayList<Integer>getNbTauxBiere();

    @Query("select  count(b.version) from Biere as b group by b.marque.nomMarque")
    public ArrayList<Integer>getNbVersionParMarque();

    @Query("Select b.marque.nomMarque from Biere as b group by b.marque.nomMarque")
    public ArrayList<String> getNomMarque();

    @Query("Select b from Biere as b where b.marque.brasserie.codeBrasserie = :code order by b.marque.nomMarque,b.version asc")
    public ArrayList<Biere> getBiereBrasserie(String code);

}
