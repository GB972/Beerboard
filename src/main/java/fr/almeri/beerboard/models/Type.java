package fr.almeri.beerboard.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name="type")
public class Type {
    @Id
    @Column(name="no_type")
    private Integer noType;
    @Column(name="nom_type")
    private String nomType;
    @Column(name="description")
    private String description;
    @Column(name="fermentation")
    private String fermentation;
    @Column(name="commentaire")
    private String commentaire;

    public Type(){

    }

    @Override
    public String toString() {
        return  nomType ;
    }

    public Integer getNoType() {
        return noType;
    }

    public void setNoType(Integer pNoType) {
        this.noType = pNoType;
    }

    //Renvoie l'attribut noType en String
//    public String getNoTypeStr(){
//        return String.valueOf(this.noType);
//    }

    //Set l'attribu noType à partir d'une chaine de caractère
    public void setNoTypeStr(String pNoTypeStr){
        this.noType=Integer.parseInt(pNoTypeStr);
    }

    public String getNomType() {
        return nomType;
    }

    public void setNomType(String pNomType) {
        this.nomType = pNomType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String pDescription) {
        this.description = pDescription;
    }

    public String getFermentation() {
        return fermentation;
    }

    public void setFermentation(String pFermentation) {
        this.fermentation = pFermentation;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String pCommentaire) {
        this.commentaire = pCommentaire;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Type)) return false;
        Type type = (Type) o;
        return Objects.equals(noType, type.noType) && Objects.equals(nomType, type.nomType) && Objects.equals(description, type.description) && Objects.equals(fermentation, type.fermentation) && Objects.equals(commentaire, type.commentaire);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noType, nomType, description, fermentation, commentaire);
    }
}
