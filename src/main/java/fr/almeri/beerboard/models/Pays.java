package fr.almeri.beerboard.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "pays")
public class Pays implements Serializable {
    /**
     * Entité Pays
     */
    @Id
    @Column(name = "nom_pays")
    private String nomPays;
    @Column(name = "consommation")
    private Double consommation;
    @Column(name = "production")
    private Double production;

    public Pays(){};

    /**
     *
     * @param nomPays
     * @param consommation
     * @param production
     */
    public Pays(String nomPays, Double consommation, Double production){
        this.nomPays = nomPays;
        this.consommation = consommation;
        this.production = production;
    };

    /**
     * @return String nomPays
     */
    public String getNomPays() {
        return nomPays;
    }

    /**
     * @param nomPays
     */
    public void setNomPays(String nomPays) {
        this.nomPays = nomPays;
    }

    /**
     *
     * @return Double consommation
     */
    public Double getConsommation() {
        return this.consommation;
    }

    /**
     * @param consommation
     */
    public void setConsommation(Double consommation) {
        this.consommation = consommation;
    }

    /**
     *
     * @return Double production
     */
    public Double getProduction() {
        return this.production;
    }

    /**
     *
     * @param production
     */
    public void setProduction(Double production) {
        this.production = production;
    }

    @Override
    public String toString() {
        return "Pays{" +
                "nomPays='" + this.getNomPays() + '\'' +
                ", consommation=" + this.getConsommation() +
                ", production=" + this.getProduction() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pays)) return false;
        Pays pays = (Pays) o;
        return Objects.equals(getNomPays(), pays.getNomPays()) && Objects.equals(getConsommation(), pays.getConsommation()) && Objects.equals(getProduction(), pays.getProduction());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNomPays(), getConsommation(), getProduction());
    }
}
