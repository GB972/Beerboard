package fr.almeri.beerboard.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

public class BiereId implements Serializable {

    @Id
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nom_marque")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Marque marque;
    @Id
    @Column(name = "version")
    private String version;

    public BiereId(){};
    public BiereId(Marque marque, String version)
    {
        this.marque = marque;
        this.version = version;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BiereId)) return false;
        BiereId biereId = (BiereId) o;
        return Objects.equals(getMarque(), biereId.getMarque()) && Objects.equals(getVersion(), biereId.getVersion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMarque(), getVersion());
    }
}
