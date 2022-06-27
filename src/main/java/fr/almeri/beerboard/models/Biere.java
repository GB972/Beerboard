package fr.almeri.beerboard.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "biere")
@IdClass(BiereId.class)
public class Biere implements Serializable{

    /**
     * Entité Biere
     */
    @Id
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nom_marque")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Marque marque;
    @Id
    @Column(name = "version")
    private String version;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "no_type")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Type type;

    @Column(name = "couleur_biere")
    private String couleurBiere;
    @Column(name = "taux_alcool")
    private Double tauxAlcool;
    @Column(name = "caracteristiques")
    private String caracteristiques;
    @Transient
    private String noTypeStr;

    public Biere(){};

    /**
     *
     * @param marque
     * @param version
     * @param type
     * @param couleurBiere
     * @param tauxAlcool
     * @param caracteristiques
     */
    public Biere(Marque marque, String version, Type type, String couleurBiere, Double tauxAlcool, String caracteristiques){

        this.marque = marque;
        this.version = version;
        this.type = type;
        this.couleurBiere = couleurBiere;
        this.tauxAlcool = tauxAlcool;
        this.caracteristiques = caracteristiques;
    };

    /**
     * @return Marque
     */
    public Marque getMarque() {
        return marque;
    }

    /**
     * @param marque
     */
    public void setMarque(Marque marque) {
        this.marque = marque;
    }

    /**
     * @return String version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * @return Type
     */
    public Type getType() {
        return this.type;
    }

    /**
     * @param type
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * @return String couleurBiere
     */
    public String getCouleurBiere() {
        return couleurBiere;
    }

    /**
     * @param couleurBiere
     */
    public void setCouleurBiere(String couleurBiere) {
        this.couleurBiere = couleurBiere;
    }

    /**
     * @return Double tauxAlcool
     */
    public Double getTauxAlcool() {
        return tauxAlcool;
    }

    /**
     * @param tauxAlcool
     */
    public void setTauxAlcool(Double tauxAlcool) {
        this.tauxAlcool = tauxAlcool;
    }

    /**
     * @return String caracteristique
     */
    public String getCaracteristiques() {
        return caracteristiques;
    }

    /**
     * @param caracteristiques
     */
    public void setCaracteristiques(String caracteristiques) {
        this.caracteristiques = caracteristiques;
    }

    /**
     * @return String noTyeStr
     */
    public String getNoTypeStr() {
        return noTypeStr;
    }

    /**
     * @param noTypeStr
     */
    public void setNoTypeStr(String noTypeStr) {
        this.noTypeStr = noTypeStr;
    }

    @Override
    public String toString() {
        return "Biere{" +
                "marque=" + this.getMarque() +
                ", version='" + this.getVersion() + '\'' +
                ", type=" + this.getType() +
                ", couleurBiere='" + this.getCouleurBiere() + '\'' +
                ", tauxAlcool=" + this.getTauxAlcool() +
                ", caracteristiques='" + this.getCaracteristiques() + '\'' +
                ", noTypeStr='" + this.getNoTypeStr() + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Biere)) return false;
        Biere biere = (Biere) o;
        return Objects.equals(getMarque(), biere.getMarque()) && Objects.equals(getVersion(), biere.getVersion()) && Objects.equals(getType(), biere.getType()) && Objects.equals(getCouleurBiere(), biere.getCouleurBiere()) && Objects.equals(getTauxAlcool(), biere.getTauxAlcool()) && Objects.equals(getCaracteristiques(), biere.getCaracteristiques()) && Objects.equals(getNoTypeStr(), biere.getNoTypeStr());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMarque(), getVersion(), getType(), getCouleurBiere(), getTauxAlcool(), getCaracteristiques(), getNoTypeStr());
    }
}
