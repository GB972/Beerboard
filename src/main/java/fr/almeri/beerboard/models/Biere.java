package fr.almeri.beerboard.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="biere")
@IdClass(BiereId$.class)
public class Biere {
    @Id
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="nom_marque")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Marque marque;

    @Id
    @Column(name="version")
    private String version;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="no_type")
    private Type type;
    @Column(name="couleur_biere")
    private String couleurBiere;
    @Column(name="taux_alcool")
    private Double tauxAlcool;
    @Column(name="caracteristiques")
    private String caracteristiques;
//    @Column(name="noTypeStr")
//    private String noTypeStr;

    public Biere(){

    }

    public Marque getMarque() {
        return marque;
    }

    public void setMarque(Marque pMarque) {
        this.marque = pMarque;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String pVersion) {
        this.version = pVersion;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type pType) {
        this.type = pType;
    }

    public String getCouleurBiere() {
        return couleurBiere;
    }

    public void setCouleurBiere(String pCouleurBiere) {
        this.couleurBiere = pCouleurBiere;
    }

    public Double getTauxAlcool() {
        return tauxAlcool;
    }

    public void setTauxAlcool(Double pTauxAlcool) {
        this.tauxAlcool = pTauxAlcool;
    }

    public String getCaracteristiques() {
        return caracteristiques;
    }

    public void setCaracteristiques(String pCaracteristiques) {
        this.caracteristiques = pCaracteristiques;
    }

//    public String getNoTypeStr() {
//        return noTypeStr;
//    }
//
//    public void setNoTypeStr(String pNoTypeStr) {
//        this.noTypeStr = pNoTypeStr;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Biere)) return false;
        Biere biere = (Biere) o;
        return Objects.equals(marque, biere.marque) && Objects.equals(version, biere.version) && Objects.equals(type, biere.type) && Objects.equals(couleurBiere, biere.couleurBiere) && Objects.equals(tauxAlcool, biere.tauxAlcool) && Objects.equals(caracteristiques, biere.caracteristiques);
    }

    @Override
    public int hashCode() {
        return Objects.hash(marque, version, type, couleurBiere, tauxAlcool, caracteristiques);
    }
}
