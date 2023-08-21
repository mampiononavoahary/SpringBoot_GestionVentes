package com.example.springbootproject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Produit {
    private int id_produit;
    private String nom_produit;
    private String description;
    private String type;
    private double prix;
    private int quantiter_stock;

    @Override
    public String toString() {
        return "Produit{" +
                "id_produit=" + id_produit +
                ", nom_produit='" + nom_produit + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", prix=" + prix +
                ", quantiter_stock=" + quantiter_stock +
                '}';
    }
}
