package fr.almeri.beerboard.controllers;

import fr.almeri.beerboard.models.User;
import fr.almeri.beerboard.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

public class UserControlleur {
    @Autowired
    private UserRepository userRepository;

    static byte[] getSalt() throws NoSuchAlgorithmException, NoSuchProviderException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }

    static String hashMD5withSalt(String passwordToHash, byte[] salt){
        String generatedPassword = null;
        MessageDigest md = null;
        try{
            md = MessageDigest.getInstance("MD5");

        } catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        md.update(salt);
        byte[] bytes= md.digest(passwordToHash.getBytes());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i< bytes.length; i++){
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100,16).substring(1));
        }
        generatedPassword = sb.toString();
        return generatedPassword;
    }

    public boolean checkPassword(User user){
        User u = userRepository.getUser(user.getLogin());
        if (u == null){
            return false;
        }

        String newPass = hashMD5withSalt(user.getPassword(),u.getSalt());
        return newPass.equals(u.getPassword());
    }

}
