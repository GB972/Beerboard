package fr.almeri.beerboard.models;

import javax.persistence.*;
import java.util.Objects;

//Entity et table : j'indique la table qui correspond à l'objet Pays
@Entity
@Table(name="pays")
public class Pays {

    //ATTRIBUTS
    //@Id : j'indique l'attribut avec la clé primaire
    //@Column je fais la liaison entre l'attribut et le nom de la colonne
    @Id
    @Column(name ="nom-pays")
    private String nomPays;
    @Column(name="consommation")
    private Double consommation;
    @Column(name="production")
    private Double production;


    public Pays(){

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pays)) return false;
        Pays pays = (Pays) o;
        return Objects.equals(nomPays, pays.nomPays) && Objects.equals(consommation, pays.consommation) && Objects.equals(production, pays.production);
    }

    @Override
    public String toString() {
        return   nomPays ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomPays, consommation, production);
    }

    public String getNomPays() {
        return nomPays;
    }

    public void setNomPays(String pNomPays) {
        this.nomPays = pNomPays;
    }

    public Double getConsommation() {
        return consommation;
    }

    public void setConsommation(Double pConsommation) {
        this.consommation = pConsommation;
    }

    public Double getProduction() {
        return production;
    }

    public void setProduction(Double pProduction) {
        this.production = pProduction;
    }
}
