package com.example.springbootproject.controller;

import ch.qos.logback.core.model.INamedModel;
import com.example.springbootproject.Service.ProduitService;
import com.example.springbootproject.model.Produit;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProduitController {
    private ProduitService produitService;

    public ProduitController(ProduitService produitService) {
        this.produitService = produitService;
    }

    @GetMapping("/Allproduct")
    public List<Produit> Allproduct()throws Exception{
        return produitService.AllProduct();
    }

    @PostMapping("/Addproduct")
    public String AddProduct(@RequestBody Produit produit)throws Exception{
        return produitService.InsertProduct(produit);
    }

    @PutMapping("/UpdateProduct/{id_produit}")
    public String UpdateProduct(@RequestBody Produit produit)throws Exception{
        return produitService.UpdateProduit(produit);
    }

    @GetMapping("/findProduct/{id_produit}")
    public Optional<Produit> findProduit(@PathVariable int id_produit)throws Exception{
        return produitService.findByid(id_produit);
    }

    @DeleteMapping("/deleteProduct/{id_produit}")
    public String DeleteProduct(@PathVariable int id_produit)throws Exception{
        return produitService.DeleteProduct(id_produit);
    }
}
