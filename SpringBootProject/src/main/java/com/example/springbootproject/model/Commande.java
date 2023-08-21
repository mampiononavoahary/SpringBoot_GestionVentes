package com.example.springbootproject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class Commande {
    private int id_commande;
    private int id_client;
    private int id_employee;
    private Date date_commande;

    @Override
    public String toString() {
        return "Commande{" +
                "id_commande=" + id_commande +
                ", id_client=" + id_client +
                ", id_employee=" + id_employee +
                ", date_commande=" + date_commande +
                '}';
    }
}
