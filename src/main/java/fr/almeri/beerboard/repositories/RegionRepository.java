package fr.almeri.beerboard.repositories;


import fr.almeri.beerboard.models.Region;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface RegionRepository extends CrudRepository<Region,String> {

}
