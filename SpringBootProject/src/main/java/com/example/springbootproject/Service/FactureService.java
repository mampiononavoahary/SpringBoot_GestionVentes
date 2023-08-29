package com.example.springbootproject.Service;

import com.example.springbootproject.model.Facture;
import com.example.springbootproject.repository.FactureDAO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FactureService {
    private FactureDAO factureDAO;

    public FactureService(FactureDAO factureDAO) {
        this.factureDAO = factureDAO;
    }

    public List<Facture> AllFacture()throws Exception{
        try {
            return factureDAO.Allfacture();
        }catch (Exception e){
            throw new RuntimeException("Facture not found because: "+e);
        }
    }

    public String InsertFacture(Facture facture)throws Exception{
        try {
            this.factureDAO.InsertFacture(facture);

            return "facture inserted";
        }catch (Exception e){
            throw new RuntimeException("You can not add facture because "+e);
        }
    }

    public String updateFacture(Facture facture)throws Exception{
        try {
            factureDAO.UpdateFacture(facture);

            return "facture already up to date";
        }catch (Exception e){
            throw new RuntimeException("you can not update this facture "+e);
        }
    }

    public Optional<Facture> findFacture(int id_facture)throws Exception{
        try {
            return factureDAO.findFacture(id_facture);
        }catch (Exception e){
            throw new RuntimeException("Id facture not found");
        }
    }

    public String deleteFacture(int id_facture) throws Exception{
        try {
            factureDAO.DeleteFacture(id_facture);
        }catch (Exception e){
            throw new RuntimeException("You can not delete this facture with id: "+id_facture+" because: "+e);
        }

        return "The facture with is: "+id_facture+"has been deleted";
    }
}
