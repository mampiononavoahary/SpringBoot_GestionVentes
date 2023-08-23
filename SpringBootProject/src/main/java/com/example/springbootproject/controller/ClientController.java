package com.example.springbootproject.controller;

import com.example.springbootproject.Service.ClientService;
import com.example.springbootproject.model.Client;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ClientController {
    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/ListClient")
    public List<Client> ListClient() throws Exception{
        return clientService.AllClients();
    }

    @PostMapping("/insertClient")
   public Client insertClient(@RequestBody Client insertclient) throws Exception{
        return clientService.insert(insertclient);
    }

    @PutMapping("/UpdateClient/{id_client}")
    public Client updateClient(@PathVariable int id_client, @RequestBody Client upClient)throws Exception{
        if (upClient.getId_client() != id_client){
            throw new RuntimeException("Id invalide");
        }
        return clientService.Update(upClient);
    }

    @GetMapping("/ClientId/{id_client}")
    public Optional<Client> finById(@PathVariable int id_client) throws Exception{
        return clientService.findClientByid(id_client);
    }

    @DeleteMapping("/deleteClient/{id_client}")
    public ResponseEntity<String> deleteClient(@PathVariable int id_client){
        clientService.deleteClient(id_client);
        return ResponseEntity.ok("The client with id "+id_client+" has been deleted");
    }
}
