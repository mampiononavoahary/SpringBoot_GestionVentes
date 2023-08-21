package com.example.springbootproject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Client extends Utilisateur {
        private int id_client;
    public Client(int id_client, String nom, String prenom, String email, String address, String contacts, String genre) {
        super(nom, prenom, email, address, contacts, genre);
        this.id_client = id_client;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id_client=" + id_client +
                '}';
    }
}
