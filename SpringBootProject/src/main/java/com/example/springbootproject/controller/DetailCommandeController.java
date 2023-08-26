package com.example.springbootproject.controller;

import com.example.springbootproject.Service.DetailCommandeService;
import com.example.springbootproject.model.DetailCommande;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class DetailCommandeController {
    private DetailCommandeService detailCommandeService;

    public DetailCommandeController(DetailCommandeService detailCommandeService) {
        this.detailCommandeService = detailCommandeService;
    }

    @GetMapping("/AllDetailCommande")
    public List<DetailCommande> AllDetailCommande() throws Exception{
        return detailCommandeService.AllDetailCommande();
    }

    @PostMapping("/InsertDetailCommande")
    public String InsertDetailCommande(@RequestBody DetailCommande detailCommande) throws Exception{
        return detailCommandeService.InsertDetailCommande(detailCommande);
    }

    @PutMapping("/updateDetailCommande/{id_detail_commande}")
    public String updateDetailCommande(@PathVariable int id_detail_commande, @RequestBody DetailCommande detailCommande) throws Exception{
        if (detailCommande.getId_detail_commande() != id_detail_commande){
            return "Id detail commande not found";
        }
        return detailCommandeService.UpdateDetailCommande(detailCommande);
    }

    @DeleteMapping("/deletedetailCommande/{id_detail_commande}")
    public ResponseEntity<String> deleteDetailCommande(@PathVariable int id_detail_commande) throws Exception{
        detailCommandeService.detleteDetailCommande(id_detail_commande);

        return ResponseEntity.ok("Detail commande with id :"+id_detail_commande+" is deleted");
    }

    @GetMapping("/findDetailCommande/{id_detail_commande}")
    public Optional<DetailCommande> findById(@PathVariable int id_detail_commande)throws Exception{
        return detailCommandeService.findDetailCommande(id_detail_commande);
    }
}
