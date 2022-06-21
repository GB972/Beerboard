package fr.almeri.beerboard.repositories;

import fr.almeri.beerboard.models.Brasserie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface BrasserieRepository extends CrudRepository<Brasserie,String> {

    @Query("Select b.nomBrasserie from Brasserie b group by b.codeBrasserie order by b.codeBrasserie")
    public ArrayList<String> getBrasserie();

    @Query("select count(b.codeBrasserie) from Brasserie b ")
    public int getNbBrasserie();

    @Query("select b.region.nomRegion from Brasserie b group by b.region.nomRegion order by b.region.nomRegion asc")
    public List<String> getLabelsNomRegion();

    @Query("select count(b) from Brasserie  b  group by b.region.nomRegion order by b.region.nomRegion")
    public ArrayList<Integer> getNbBrasserieRegion();



}
