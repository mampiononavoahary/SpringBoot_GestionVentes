package com.example.springbootproject.Service;

import com.example.springbootproject.model.DetailCommande;
import com.example.springbootproject.repository.DetailCommandeDAO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetailCommandeService {
    private DetailCommandeDAO detailCommandeDAO;

    public DetailCommandeService(DetailCommandeDAO detailCommandeDAO) {
        this.detailCommandeDAO = detailCommandeDAO;
    }

    public List<DetailCommande> AllDetailCommande()throws Exception{
        try {
            return detailCommandeDAO.AllDetailCommande();
        }catch (Exception e){
            throw new RuntimeException("Detail Commande not found because :"+e);
        }
    }

    public String InsertDetailCommande(DetailCommande detailCommande){
        try {
            this.detailCommandeDAO.InsetDetailCommande(detailCommande);
            return "Inserted successfuly";
        }catch (Exception e){
            throw new RuntimeException("Error in Inserted"+e);
        }
    }

    public String UpdateDetailCommande(DetailCommande detailCommande){
        try {
             this.detailCommandeDAO.UpdateDetailCommande(detailCommande);
        }catch (Exception e){
            throw new RuntimeException("Detail commande not up to date"+e);
        }

        return "Dtetail commande with id"+detailCommande.getId_detail_commande()+" already up to date";
    }

    public String detleteDetailCommande(int id_detail_commande){
        try {
            detailCommandeDAO.deleteDetailCommande(id_detail_commande);
        }catch (Exception e){
            throw new RuntimeException("you can not delete the detail_commande with id "+id_detail_commande);
        }

        return "The detail_commande with id "+id_detail_commande+" is deleted";
    }

    public Optional<DetailCommande> findDetailCommande(int id_detail_commande)throws Exception{
        try {
            return detailCommandeDAO.FindById(id_detail_commande);
        }catch (Exception e){
            throw new RuntimeException("Detail commande not found"+e);
        }
    }
}
