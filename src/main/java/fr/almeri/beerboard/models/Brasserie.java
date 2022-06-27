package fr.almeri.beerboard.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "brasserie")
public class Brasserie implements Serializable {

    /**
     * Entité Brasserie
     */
    @Id
    @Column(name = "code_brasserie")
    private String codeBrasserie;
    @Column(name = "nom_brasserie")
    private String nomBrasserie;
    @Column(name = "ville")
    private String ville;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Region region;

    public Brasserie(){};

    /**
     *
     * @param codeBrasserie
     * @param nomBrasserie
     * @param ville
     * @param region
     */
    public Brasserie(String codeBrasserie, String nomBrasserie, String ville, Region region)
    {
        this.codeBrasserie = codeBrasserie;
        this.nomBrasserie = nomBrasserie;
        this.ville = ville;
        this.region = region;
    }

    /**
     * @return String codeBrasserie
     */
    public String getCodeBrasserie() {
        return this.codeBrasserie;
    }

    /**
     * @param codeBrasserie
     */
    public void setCodeBrasserie(String codeBrasserie) {
        this.codeBrasserie = codeBrasserie;
    }

    /**
     * @return String nomBrasserie
     */
    public String getNomBrasserie() {
        return this.nomBrasserie;
    }

    /**
     * @param nomBrasserie
     */
    public void setNomBrasserie(String nomBrasserie) {
        this.nomBrasserie = nomBrasserie;
    }

    /**
     * @return String ville
     */
    public String getVille() {
        return this.ville;
    }

    /**
     * @param ville
     */
    public void setVille(String ville) {
        this.ville = ville;
    }

    /**
     * @return Region
     */
    public Region getRegion() {
        return this.region;
    }

    /**
     * @param region
     */
    public void setRegion(Region region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "Brasserie{" +
                "codeBrasserie='" + this.getCodeBrasserie() + '\'' +
                ", nomBrasserie='" + this.getNomBrasserie()+ '\'' +
                ", ville='" + this.getVille() + '\'' +
                ", region=" + this.getRegion() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Brasserie)) return false;
        Brasserie brasserie = (Brasserie) o;
        return Objects.equals(getCodeBrasserie(), brasserie.getCodeBrasserie()) && Objects.equals(getNomBrasserie(), brasserie.getNomBrasserie()) && Objects.equals(getVille(), brasserie.getVille()) && Objects.equals(getRegion(), brasserie.getRegion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCodeBrasserie(), getNomBrasserie(), getVille(), getRegion());
    }
}
