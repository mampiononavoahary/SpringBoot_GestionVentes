package com.example.springbootproject.Service;

import com.example.springbootproject.model.Commande;
import com.example.springbootproject.repository.CommandeDAO;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class CommandeService {
    private CommandeDAO commandeDAO;

    public CommandeService(CommandeDAO commandeDAO) {
        this.commandeDAO = commandeDAO;
    }
    public List<Commande> AllCommande() throws Exception{
        try {
            return commandeDAO.AllCommande();
        }catch (Exception e){
            throw new RuntimeException("List Commande not found");
        }

    }

    public String InsertCommande(Commande commande){
        try {
            this.commandeDAO.InsertCommande(commande);

            return "Inserted  succesfuly";
        }catch (Exception e){
            throw new RuntimeException("You can not insert commande because: "+e+"");
        }
    }

    public Optional<Commande> FindCommande(int id_commande)throws Exception{
        try {
            return commandeDAO.finCommande(id_commande);
        }catch (Exception e){
            throw new RuntimeException("Commande with id :"+id_commande+" not found");
        }
    }

    public String UpdateCommande(Commande update){
        try {
            this.commandeDAO.UpdateCommande(update);
        }catch (Exception e){
           throw new RuntimeException("You can not update this commande with id:" +update.getId_commande()+"because :"+e);
        }
        return "Commande up to date";
    }

    public String deleteCommande(int id_commande) throws Exception{
        try {
            commandeDAO.deleteCommande(id_commande);
        }catch (Exception e){
            throw new RuntimeException("You can not delete this commande with id: "+id_commande+" because: "+e);
        }

        return "The commande with is: "+id_commande+"has been deleted";
    }
}
