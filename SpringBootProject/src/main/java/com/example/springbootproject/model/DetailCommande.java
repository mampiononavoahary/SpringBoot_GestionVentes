package com.example.springbootproject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class DetailCommande {
    private int detail_commande;
    private int id_commande;
    private int id_produit;
    private double prix_unitaire;
    private int quantite;
    private String mode_payment;
    private boolean status_payment;

    @Override
    public String toString() {
        return "DetailCommande{" +
                "detail_commande=" + detail_commande +
                ", id_commande=" + id_commande +
                ", id_produit=" + id_produit +
                ", prix_unitaire=" + prix_unitaire +
                ", quantite=" + quantite +
                ", mode_payment='" + mode_payment + '\'' +
                ", status_payment=" + status_payment +
                '}';
    }
}
