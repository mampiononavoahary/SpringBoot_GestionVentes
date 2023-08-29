package com.example.springbootproject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class Facture {
    private int id_facture;
    private int id_commande;
    private LocalDate date_facturation;
    private boolean montant_statut;

    @Override
    public String toString() {
        return "Facture{" +
                "id_facture=" + id_facture +
                ", id_commande=" + id_commande +
                ", date_facturation=" + date_facturation +
                ", montant_statut=" +montant_statut+
                '}';
    }
}
