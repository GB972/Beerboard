package fr.almeri.beerboard.models;

import javax.persistence.*;
import java.io.Serializable;

//class mock
//qui correspond à ma clef primaire
// donc 2 attributs : marque & version
public class BiereId$ implements Serializable {
    @Id
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="nom_marque")
    private Marque marque;

    @Id
    @Column(name="version")
    private String version;

    public BiereId$(){

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
}
