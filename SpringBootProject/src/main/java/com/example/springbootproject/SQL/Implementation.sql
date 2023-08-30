-- Si vous ne voulez pas faire trop d'insertion, voici quelque insertion afain que vous
-- pouvez faire une teste --

INSERT INTO Client VALUES (1,'John','Doe','John@gmail.com','Marseille','0220238493','Homme'),(2,'Mak',
'Alister','Alister@gmail.com','Bretaigne','3234085454','Homme');

INSERT INTO Employee VALUES (1,'Ester','Loan','Loan@gmail.com','Receptioniste','3459456940','Femme','Paris'),(2,
'David','Johns','Johns@gmail.com','Receptioniste','347584554','Homme','Paris');

INSERT INTO produit VALUES (1,'sucre','produit naturel',10,2000,'nouriture'),(2,'riz','Produit local',200,20000,'nouriture');

INSERT INTO commande VALUES (1,1,'2023-12-12',1),(2,2,'2023-08-30',2);

INSERT INTO detail_commande VALUES (1,1,1,5,'mobile money'),(2,2,2,100,'cash');

INSERT INTO facture VALUES (1,1,'2023-12-23',true),(2,2,'2023-09-12',false);