package com.example.springbootproject.Service;

import com.example.springbootproject.model.Client;
import com.example.springbootproject.repository.ClientDAO;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    private ClientDAO ClientDAO;

    public ClientService(com.example.springbootproject.repository.ClientDAO clientDAO) {
        ClientDAO = clientDAO;
    }

    public List<Client> AllClients()throws Exception{
       return ClientDAO.findAll();
   }

   public Client insert(Client client){
       try {
           this.ClientDAO.InsertClient(client);

           return client;
       }catch (Exception e){
           throw new RuntimeException(e);
       }
   }

   public Client Update(Client client) throws Exception{
        try {
            this.ClientDAO.UpateClient(client);

            return client;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
   }

   public Optional<Client> findClientByid(int id_client){
        try {
            return ClientDAO.findAClient(id_client);
        }catch (Exception e){
            throw new RuntimeException("Id client not found");
        }
   }

   public void deleteClient(int id_client){
        try {
           ClientDAO.deleteClient(id_client);
        }catch (Exception e){
            throw new RuntimeException("You could not delete a client id "+ id_client);
        }
   }
}
