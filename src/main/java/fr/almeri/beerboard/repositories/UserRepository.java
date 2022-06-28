package fr.almeri.beerboard.repositories;

import fr.almeri.beerboard.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("SELECT user.login from User user WHERE user.login = :login ")
    public User getUser(String login);

    @Query("SELECT user from User user WHERE user.login = :login")
    public User compteExistant(String login);
}





