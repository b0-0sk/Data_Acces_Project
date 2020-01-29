CREATE DATABASE muntatge;
USE muntatge;
CREATE TABLE clients(

		id_client  VARCHAR(4) NOT NULL,
		cif CHAR(9) NOT NULL,
		name  VARCHAR(30) NOT NULL,
		surname VARCHAR(30),
		telf int(9) NOT NULL,
		email VARCHAR(30),
		cp int(5) NOT NULL,
		province VARCHAR(100) NOT NULL,
		iban VARCHAR(30) NOT NULL,
		goodPayer boolean NOT NULL,
)

INSERT INTO clients (id_client,cif,name,surname,telf,email,cp,province,iban,goodPayer)
VALUES ("aaaa",123456789,"Pcompanentes"," ",623456789,"pcComponentes@gmail.com",08760,"Barcelona, C/Callefalsa 123","E123456773535",0);

INSERT INTO clients (id_client,cif,name,surname,telf,email,cp,province,iban,goodPayer)
VALUES ("aaab",123456789,"Sergi","Reyes ",623456789,"sergireyess@gmail.com",08760,"Barcelona, C/Callefalsa 123","E123456773535",0);

INSERT INTO clients (id_client,cif,name,surname,telf,email,cp,province,iban,goodPayer)
VALUES ("aaac",123456789,"Arnau","Bosch ",623456789,"arnaubosch@gmail.com",08760,"Barcelona, C/Callefalsa 123","E123456773535",1);

INSERT INTO clients (id_client,cif,name,surname,telf,email,cp,province,iban,goodPayer)
VALUES ("aaad",123456789,"Natalia","Suarez ",623456789,"nataliasuarez@gmail.com",08760,"Barcelona, C/Callefalsa 123","E123456773535",0);

INSERT INTO clients (id_client,cif,name,surname,telf,email,cp,province,iban,goodPayer)
VALUES ("aaae",123456789,"Xevi","Benet ",623456789,"xevibenet@gmail.com",08760,"Barcelona, C/Callefalsa 123","E123456773535",1);




)
