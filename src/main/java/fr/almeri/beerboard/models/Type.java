package fr.almeri.beerboard.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "type")
public class Type implements Serializable {

    /**
     * Entité Type
     */
    @Id
    @Column(name = "no_type")
    private Integer noType;
    @Column(name = "nom_type")
    private String nomType;
    @Column(name = "description")
    private String description;
    @Column(name = "fermentation")
    private String fermentation;
    @Column(name = "commentaire")
    private String commentaire;

    public Type(){};
    public Type(String nomType){
        this.nomType = nomType;
    };

    /**
     *
     * @param noType
     * @param nomType
     * @param description
     * @param fermentation
     * @param commentaire
     */
    public Type(Integer noType, String nomType, String description, String fermentation, String commentaire){

        this.noType = noType;
        this.nomType = nomType;
        this.description = description;
        this.fermentation = fermentation;
        this.commentaire = commentaire;
    };

    public Integer getNoType() {
        return this.noType;
    }

    /**
     * @return String noTypeStr
     */
    public String getNoTypeStr() {
        return String.valueOf(this.noType);
    }

    /**
     * @param noType
     */
    public void setNoType(Integer noType) {
        this.noType = noType;
    }

    /**
     * @param noType
     */
    public void setNoTypeStr(String noType) {
        this.noType = Integer.parseInt(noType);
    }

    /**
     * @return String nomType
     */
    public String getNomType() {
        return this.nomType;
    }

    /**
     * @param nomType
     */
    public void setNomType(String nomType) {
        this.nomType = nomType;
    }

    /**
     * @return String description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return String fermentation
     */
    public String getFermentation() {
        return this.fermentation;
    }

    /**
     * @param fermentation
     */
    public void setFermentation(String fermentation) {
        this.fermentation = fermentation;
    }

    /**
     * @return String commentaire
     */
    public String getCommentaire() {
        return this.commentaire;
    }

    /**
     * @param commentaire
     */
    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    @Override
    public String toString() {
        return "Type{" +
                "noType=" + this.getNoType() +
                ", nomType='" + this.getNomType() + '\'' +
                ", description='" + this.getDescription() + '\'' +
                ", fermentation='" + this.getFermentation() + '\'' +
                ", commentaire='" + this.getCommentaire() + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Type)) return false;
        Type type = (Type) o;
        return Objects.equals(getNoType(), type.getNoType()) && Objects.equals(getNomType(), type.getNomType()) && Objects.equals(getDescription(), type.getDescription()) && Objects.equals(getFermentation(), type.getFermentation()) && Objects.equals(getCommentaire(), type.getCommentaire());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNoType(), getNomType(), getDescription(), getFermentation(), getCommentaire());
    }
}
