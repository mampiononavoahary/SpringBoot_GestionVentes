package com.example.springbootproject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class DetailCommande {
    private int id_detail_commande;
    private int id_commande;
    private int id_produit;
    private int quantite;
    private String mode_payment;

    @Override
    public String toString() {
        return "DetailCommande{" +
                "detail_commande=" + id_detail_commande +
                ", id_commande=" + id_commande +
                ", id_produit=" + id_produit +
                ", quantite=" + quantite +
                ", mode_payment='" + mode_payment +
                '}';
    }
}
