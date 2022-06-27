package fr.almeri.beerboard.repositories;

import fr.almeri.beerboard.models.Region;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface RegionRepository extends CrudRepository<Region, String> {

    @Query("select region.nomRegion from Region region ORDER BY region.nomRegion ASC")
    public ArrayList<String> getListeNomRegionAsc();

    @Query("select region from Region region ORDER BY region.nomRegion ASC")
    public ArrayList<Region> getListeNomRegionObjAsc();
}
