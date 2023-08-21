package com.example.springbootproject.controller;

import com.example.springbootproject.model.Client;
import com.example.springbootproject.repository.ClientDAO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientController {
    private ClientDAO repos;
    public ClientController(ClientDAO repos){
        this.repos = repos;
    }
    @GetMapping("/ListClient")
    public List<Client> ListClient() throws Exception{
        return repos.findAll();
    }
}
