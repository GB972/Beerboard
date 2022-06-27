package fr.almeri.beerboard.repositories;

import fr.almeri.beerboard.models.Pays;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;


@Repository
public interface PaysRepository extends CrudRepository<Pays,String> {

    @Query("select pays.nomPays from Pays pays order by pays.nomPays asc")
    public ArrayList<String> getListeNomPaysAsc();

    @Query("select pays.consommation from Pays pays order by pays.nomPays asc")
    public ArrayList<Integer> getListeConsommationPaysAsc();

    @Query("select pays.production from Pays pays order by pays.nomPays asc")
    public ArrayList<Integer> getListeProductionPaysAsc();
}
