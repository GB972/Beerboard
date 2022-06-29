package fr.almeri.beerboard.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "marque")
public class Marque implements Serializable {

    /**
     * Entité marque
     */
    @Id
    @Column(name = "nom_marque")
    private String nomMarque;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "code_brasserie")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Brasserie brasserie;

    public Marque(){};

    /**
     * @param nomMarque
     */
    public Marque(String nomMarque)
    {
        this.nomMarque = nomMarque;
    }

    /**
     * @return String nomMarque
     */
    public String getNomMarque() {
        return this.nomMarque;
    }

    /**
     * @param nomMarique
     */
    public void setNomMarque(String nomMarique) {
        this.nomMarque = nomMarique;
    }

    /**
     * @return Brasserie
     */
    public Brasserie getBrasserie() {
        return this.brasserie;
    }

    /**
     * @param brasserie
     */
    public void setBrasserie(Brasserie brasserie) {
        this.brasserie = brasserie;
    }

    @Override
    public String toString() {
        return "Marque{" +
                "nomMarque='" + this.getNomMarque() + '\'' +
                ", brasserie=" + this.getBrasserie() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Marque)) return false;
        Marque marque = (Marque) o;
        return Objects.equals(getNomMarque(), marque.getNomMarque()) && Objects.equals(getBrasserie(), marque.getBrasserie());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNomMarque(), getBrasserie());
    }
}
