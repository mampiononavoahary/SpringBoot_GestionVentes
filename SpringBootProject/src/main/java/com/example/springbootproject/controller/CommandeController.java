package com.example.springbootproject.controller;

import com.example.springbootproject.Service.CommandeService;
import com.example.springbootproject.model.Commande;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CommandeController {
    private CommandeService commandeService;

    public CommandeController(CommandeService commandeService) {
        this.commandeService = commandeService;
    }

    @GetMapping("/ListCommande")
    public List<Commande> AllCommande() throws Exception{
           return commandeService.AllCommande();
    }

    @PostMapping("/InsertCommande")
    public String InsertCommande(@RequestBody Commande commande){
        return commandeService.InsertCommande(commande);
    }

    @GetMapping("/Commande/{id_commande}")
    public Optional<Commande> findCommande(@PathVariable int id_commande) throws Exception{
            return commandeService.FindCommande(id_commande);
    }

    @PutMapping("/updateCommande/{id_commande}")
    public String updateCommande(@PathVariable int id_commande, @RequestBody Commande update) throws Exception{
        return commandeService.UpdateCommande(update);
    }

    @DeleteMapping("/deleCommande/{id_commande}")
    public ResponseEntity<String> deleteCommande(@PathVariable int id_commande) throws Exception{
       commandeService.deleteCommande(id_commande);

     return  ResponseEntity.ok("The commande with id: "+id_commande+" is deleted");
    }
}
