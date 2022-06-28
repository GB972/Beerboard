package fr.almeri.beerboard.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_Id")
    private Integer userId;

    @Column(name="prenom_utilisateur")
    private String prenomUtilisateur;

    @Column(name="nom_utilisateur")
    private String nomUtilisateur;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "salt")
    private byte[] salt;

    public User(){};

    public User(String prenomUtilisateur, String nomUtilisateur,String login, String password, byte[] salt) {
        this.prenomUtilisateur = prenomUtilisateur;
        this.nomUtilisateur = nomUtilisateur;
        this.login = login;
        this.password = password;
        this.salt = salt;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPrenomUtilisateur() {
        return prenomUtilisateur;
    }

    public void setPrenomUtilisateur(String pPrenomUtilisateur) {
        this.prenomUtilisateur = pPrenomUtilisateur;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String pNomUtilisateur) {
        this.nomUtilisateur = pNomUtilisateur;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User users = (User) o;
        return Objects.equals(getUserId(), users.getUserId()) && Objects.equals(getLogin(), users.getLogin()) && Objects.equals(getPassword(), users.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getLogin(), getPassword());
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", prenomUtilisateur='" + prenomUtilisateur + '\'' +
                ", nomUtilisateur='" + nomUtilisateur + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", salt=" + Arrays.toString(salt) +
                '}';
    }
}
