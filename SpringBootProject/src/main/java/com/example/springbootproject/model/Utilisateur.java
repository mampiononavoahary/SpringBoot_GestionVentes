package com.example.springbootproject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public abstract class Utilisateur {
    private String nom;
    private String prenom;
    private String email;
    private String address;
    private String contacts;
    private String genre;

    @Override
    public String toString() {
        return "Utilisateur{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", contacts='" + contacts + '\'' +
                ", genre=" + genre +
                '}';
    }
}
