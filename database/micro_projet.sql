CREATE DATABASE e_commerce;
USE e_commerce;

DROP TABLE clients;
DROP TABLE fournisseurs;
DROP TABLE categories;
DROP TABLE articles;

CREATE TABLE clients(
	id INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(30) NOT NULL,
    prenom VARCHAR(30) NOT NULL,
    email VARCHAR(50)  NOT NULL UNIQUE,
    motPasse VARCHAR(30) NOT NULL,
    addresse VARCHAR(50),
    codePostal INT,
    ville VARCHAR(15),
    tele VARCHAR(15)
);

CREATE TABLE fournisseurs(
	id INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(30) NOT NULL,
    prenom VARCHAR(30) NOT NULL,
    email VARCHAR(50) NOT NULL UNIQUE,
    motPasse VARCHAR(30) NOT NULL,
    addresse VARCHAR(50),
    codePostal INT,
    ville VARCHAR(15),
    tele VARCHAR(15)
);

CREATE TABLE categories(
	id INT PRIMARY KEY AUTO_INCREMENT,
    label VARCHAR(50) NOT NULL
);

CREATE TABLE articles(
	id INT PRIMARY KEY AUTO_INCREMENT,
    titre VARCHAR(50) NOT NULL,
    prix decimal(12, 2) NOT NULL,
    stock INT NOT NULL,
    categorie INT NOT NULL,
    fournisseur INT NOT NULL,
    description TEXT,
    photo VARCHAR(200),
    FOREIGN KEY(categorie) REFERENCES categories(id) ON DELETE CASCADE,
    FOREIGN KEY(fournisseur) REFERENCES fournisseurs(id) ON DELETE CASCADE
);

CREATE TABLE commandes(
	id INT PRIMARY KEY AUTO_INCREMENT,
    client INT NOT NULL,
    dateCommande TIMESTAMP,
    FOREIGN KEY(client) REFERENCES clients(id)
);

ALTER TABLE commandes
MODIFY dateCommande TIMESTAMP;

CREATE TABLE lignesCommande(
    commande INT NOT NULL,
    article INT NOT NULL,
    quantite INT NOT NULL,
    PRIMARY KEY(commande, article),
    FOREIGN KEY(commande) REFERENCES commandes(id) ON DELETE CASCADE,
    FOREIGN KEY(article) REFERENCES articles(id) ON DELETE CASCADE
);

SELECT article, quantite FROM lignesCommande WHERE commande = 2;

SELECT a.id, titre, prix, stock, label, description FROM articles a, categories c WHERE categorie = c.id;

INSERT INTO fournisseurs(nom, prenom, email, motPasse, addresse, codePostal, ville, tele) VALUES('', '', '', '1234', NULL, NULL, NULL, NULL);

INSERT INTO categories(label) VALUES('c1'), ('c2'), ('c3'), ('c4'), ('c5');

INSERT INTO articles(titre, prix, stock, categorie, fournisseur, description, photo) 
VALUES('Lorem ipsum dolor sit amet consectetur', 150, 12, 1, 1, 'Amet consectetur, adipisicing elit. Dolore sequi quibusdam eius debitis.', ''),
('Voluptatibus quibusdam', 100, 100, 2, 1, 'Consequuntur quis iste quisquam consequatur eum. Excepturi similique quasi eos. Voluptatibus quibusdam ratione ullam itaque. Corporis.', ''),
('Corporis', 300, 24, 4, 1, 'Iure ab reiciendis assumenda repellat reprehenderit quia possimus.', ''),
('Excepturi similique', 100, 3, 2, 1, 'blanditiis laudantium enim provident sint perspiciatis fugit voluptatibus eligendi quidem nulla totam.', ''),
('Praesentium quibusdam', 124, 12, 3, 1, 'ipsum dolor sit amet consectetur adipisicing elit', '');

INSERT INTO articles(titre, prix, stock, categorie, fournisseur, description, photo) 
VALUES('Lorem ipsum dolor sit amet consectetur', 150, 12, 1, 1, 'Amet consectetur, adipisicing elit. Dolore sequi quibusdam eius debitis.', '');

UPDATE categories SET label = 'Electronics' WHERE id = 1;
UPDATE categories SET label = 'Books' WHERE id = 2;
UPDATE categories SET label = 'Movies' WHERE id = 3;
UPDATE categories SET label = 'Software' WHERE id = 4;
UPDATE categories SET label = 'Sports' WHERE id = 5;

DELETE FROM fournisseurs WHERE id = 1;
DELETE FROM clients WHERE id = 1;

UPDATE articles SET titre = 'Consectetur adipisicing elit', description = 'voluptatibus eligendi quidem nulla totam. Iure ab reiciendis assumenda repellat' WHERE id = 1;

INSERT INTO articles(titre, prix, stock, categorie, fournisseur, description, photo) VALUES(?, ?, ?, ?, ?, ?, ?);

SELECT c.id, label, COUNT(a.id) articles FROM categories c LEFT JOIN articles a ON c.id = a.categorie GROUP BY c.id, label;

INSERT INTO commandes(client, dateCommande) VALUES(1, '2019-3-30');
INSERT INTO lignesCommande(commande, article, quantite) VALUES(1, 1, 5), (1, 2, 3);


Select * from fournisseurs;
Select * from clients ORDER BY id DESC;
Select * from categories;
Select * from commandes;
DELETE FROM  commandes WHERE id = 4;
SELECT cm.id, CONCAT(nom, ' ', prenom) client, dateCommande, SUM(quantite) quantite, SUM(quantite * prix)
FROM commandes cm, articles a, clients cl, lignesCommande lc 
WHERE cm.client = cl.id AND cm.id = lc.commande AND lc.article = a.id
GROUP BY cm.id, dateCommande, nom, prenom;
Select * from articles;
Select * from lignesCommande;

UPDATE articles SET photo = 'img/articles/article.jpg' WHERE id = 1;
UPDATE articles SET photo = 'img/articles/article.jpg' WHERE id = 2;
UPDATE articles SET photo = 'img/articles/article.jpg' WHERE id = 3;
UPDATE articles SET photo = 'img/articles/article.jpg' WHERE id = 4;
UPDATE articles SET photo = 'img/articles/article.jpg' WHERE id = 5;
UPDATE articles SET photo = 'img/articles/article.jpg' WHERE id = 6;

UPDATE articles SET photo = 'img/articles/ar_0.jpg' WHERE id = 8;
UPDATE articles SET photo = 'img/articles/ar_0.jpg' WHERE id = 9;
UPDATE articles SET photo = 'img/articles/ar_0.jpg' WHERE id = 10;

SELECT quantite, quantite * prix
FROM articles a, lignesCommande lc 
WHERE lc.article = a.id