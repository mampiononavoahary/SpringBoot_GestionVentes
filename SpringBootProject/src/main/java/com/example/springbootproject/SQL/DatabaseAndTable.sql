-- Create database --
CREATE DATABASE gestionventes;

     \c gestionventes;

CREATE TABLE Client (
    id_client int primary key not null ,
    nom varchar(200),
    prenom varchar(200),
    email varchar (200),
    address varchar(200),
    contacts varchar(100),
    genre varchar(10)
);

CREATE TABLE Employee(
    id_employee int primary key not null,
    nom varchar(200),
    prenom varchar(200),
    email varchar(200),
    poste varchar(100),
    contacts varchar(100),
    genre varchar (10),
    address varchar(200)
);

CREATE TABLE produit(
    id_produit int primary key not null ,
    nom_produit varchar(200),
    description text,
    quantiter_stock int,
    prix double precision,
    type varchar(100)
);

CREATE TABLE commande(
    id_commande int primary key not null ,
    id_client int references Client(id_client),
    date_commande date,
    id_employee int references Employee(id_employee)
);

CREATE TABLE detail_commande(
    id_detail_commande int primary key not null ,
    id_commande int references commande(id_commande),
    id_produit int references produit(id_produit),
    quantiter int,
    mode_payment varchar(20)
);

CREATE TABLE facture(
    id_facture int not null ,
    id_commande int references commande(id_commande),
    date_facturation date,
    montant_statut boolean
);

