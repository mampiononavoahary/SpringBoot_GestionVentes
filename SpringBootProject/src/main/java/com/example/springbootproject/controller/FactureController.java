package com.example.springbootproject.controller;

import com.example.springbootproject.Service.FactureService;
import com.example.springbootproject.model.Facture;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class FactureController {

    private FactureService factureService;

    public FactureController(FactureService factureService) {
        this.factureService = factureService;
    }

    @GetMapping("/ListFacture")
    public List<Facture> Allfacture() throws Exception{
        return factureService.AllFacture();
    }

    @PostMapping("/InsertFacture")
    public String InsertCFacture(@RequestBody Facture facture) throws Exception{
        return factureService.InsertFacture(facture);
    }

    @GetMapping("/Facture/{id_facture}")
    public Optional<Facture> findFacture(@PathVariable int id_facture) throws Exception{
        return factureService.findFacture(id_facture);
    }

    @PutMapping("/updateFacture/{id_facture}")
    public String updateFacture(@RequestBody Facture update) throws Exception{
        return factureService.updateFacture(update);
    }

    @DeleteMapping("/deleFacture/{id_facture}")
    public ResponseEntity<String> deleteFacture(@PathVariable int id_facture) throws Exception{
        factureService.deleteFacture(id_facture);

        return  ResponseEntity.ok("The Facture with id: "+id_facture+" is deleted");
    }
}
