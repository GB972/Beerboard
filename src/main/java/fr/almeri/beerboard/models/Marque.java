package fr.almeri.beerboard.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name ="marque")
public class Marque  {

    @Id
    @Column(name="nomMarque")
    private String nomMarque;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="brasserie")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Brasserie brasserie;

    public Marque(){

    }

    public Marque(String pNomMarque){
        this.nomMarque=pNomMarque;
    }

    public String getNomMarque() {
        return nomMarque;
    }

    public void setNomMarque(String pNomMarque) {
        this.nomMarque = pNomMarque;
    }

    public Brasserie getBrasserie() {
        return brasserie;
    }

    public void setBrasserie(Brasserie pBrasserie) {
        this.brasserie = pBrasserie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Marque)) return false;
        Marque marque = (Marque) o;
        return Objects.equals(nomMarque, marque.nomMarque) && Objects.equals(brasserie, marque.brasserie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomMarque, brasserie);
    }

    @Override
    public String toString() {
        return  nomMarque ;
    }
}
