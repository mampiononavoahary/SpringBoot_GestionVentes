package com.example.springbootproject.Service;

import com.example.springbootproject.model.Produit;
import com.example.springbootproject.repository.ProduitDAO;
import org.springframework.expression.spel.ast.OpAnd;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class ProduitService {
    private ProduitDAO produitDAO;

    public ProduitService(ProduitDAO produitDAO) {
        this.produitDAO = produitDAO;
    }

    public List<Produit> AllProduct()throws Exception{

        try{
           return produitDAO.AllProduct();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public String InsertProduct(Produit produit){
        try {
            this.produitDAO.InsertProduit(produit);
        }catch (Exception e){
            throw new RuntimeException("you can't add product"+e);
        }
        return "inserted successfuly";
    }

    public String UpdateProduit(Produit produit)throws Exception{
        try {
            this.produitDAO.UpdateProduct(produit);

            return "product already up to date";
        }catch (Exception e){
            throw new RuntimeException("you can't update this product bescause: "+e);
        }
    }

    public Optional<Produit> findByid(int id_produit)throws Exception{
        try {
            return produitDAO.findProduct(id_produit);
        }catch (Exception e) {
            throw new RuntimeException("Id not available");
        }
    }

    public String DeleteProduct(int id_produit)throws Exception{
        try {
            produitDAO.DeleteProduct(id_produit);
            return "product with id: "+id_produit+ " is deleted";
        }catch (Exception e){
            throw new RuntimeException("You can't delete this product because "+e);
        }
    }
}
