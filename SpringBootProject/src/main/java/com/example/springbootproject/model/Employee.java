package com.example.springbootproject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Employee extends Utilisateur{
    private int id_employee;
    private String poste;
    public Employee(int id_employee, String nom, String prenom, String email,String poste,String contacts,String genre, String address) {
        super(nom, prenom, email, address, contacts, genre);
        this.id_employee = id_employee;
        this.poste = poste;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id_employee=" + id_employee +
                ", poste='" + poste + '\'' +
                '}';
    }
}
