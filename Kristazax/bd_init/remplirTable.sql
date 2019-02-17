--Auteurs: Flavien Coçu
--Test rapide ajout de tuple

INSERT INTO COMPTE(nomDeCompte,mdpCompte,roleCompte,email,noteMoyenneCompte,nbNotes) VALUES ('Flavien','ea474f7dcafda10146f1b82b1900cd4c544d3fb97a8c55e129a27faa1f2889f9','Administrateur','flaflaflavien@hotmail.fr',NULL,0);
INSERT INTO COMPTE(nomDeCompte,mdpCompte,roleCompte,email,noteMoyenneCompte,nbNotes) VALUES ('Tomtom','44e84410d94b9bdd856cfe01da6a7834b95a860792c84513c64dcf12066cfb5','Normal','tomtomDG@hotmail.fr',NULL,0);

INSERT INTO ARTICLE(idVendeur, nomArticle,prixArticle,descriptionCourteArticle,descriptionLongueArticle,etat,categorie,img,lieu) VALUES (1, 'Mercedes',30000.2,'Mercedes classe A','Belle Mercedes peu kilométrée','Valide','VEHICULES','/Kristazax/image/classeA.jpg','NORD');
INSERT INTO ARTICLE(idVendeur, nomArticle,prixArticle,descriptionCourteArticle,descriptionLongueArticle,etat,categorie,img,lieu) VALUES (2, 'Slip',250.34,'Slip bleu','Beau slip bleu taille XL servi une fois','Valide','MODE','/Kristazax/image/slip.jpg','SUD');
INSERT INTO ARTICLE(idVendeur, nomArticle,prixArticle,descriptionCourteArticle,descriptionLongueArticle,etat,categorie,img,lieu) VALUES (1, 'Maison',350000.34,'Maison avec piscine','Belle maison 243m²','Valide','IMMOBILIER','/Kristazax/image/maison.jpg','SUD');
INSERT INTO ARTICLE(idVendeur, nomArticle,prixArticle,descriptionCourteArticle,descriptionLongueArticle,etat,categorie,img,lieu) VALUES (1, 'Perceuse',35.35,'Perceuse Bosch','Fais de beaux trous','Valide','MATERIELPROFESSIONNEL','/Kristazax/image/perceuseBosch.jpg','NORD');
INSERT INTO ARTICLE(idVendeur, nomArticle,prixArticle,descriptionCourteArticle,descriptionLongueArticle,etat,categorie,img,lieu) VALUES (1, 'Honor',350.34,'Honor 9 bleu','Honor 9 avec quelques rayures','Valide','MULTIMEDIA','/Kristazax/image/Honor.jpg','SUD');
INSERT INTO ARTICLE(idVendeur, nomArticle,prixArticle,descriptionCourteArticle,descriptionLongueArticle,etat,categorie,img,lieu) VALUES (1, 'Ballon',9.99,'Ballon dédicassé','Beau ballon pour jouer au foot','Valide','LOISIRS','/Kristazax/image/ballon.jpg','SUD');
INSERT INTO ARTICLE(idVendeur, nomArticle,prixArticle,descriptionCourteArticle,descriptionLongueArticle,etat,categorie,img,lieu) VALUES (1, 'Tonte',350.34,'Tonte de pelouse','Professionnel équipé pour tondre vos pelouses','Valide','SERVICES','/Kristazax/image/tondeuse.jpg','SUD');
INSERT INTO ARTICLE(idVendeur, nomArticle,prixArticle,descriptionCourteArticle,descriptionLongueArticle,etat,categorie,img,lieu) VALUES (1, 'WC',350.34,'Toilettes neuves','WC peu servie, papier toilette offert','Valide','MAISON','/Kristazax/image/WC.jpg','CENTRE');




INSERT INTO PANIER(idCompte,idArticle) VALUES (1,1);
INSERT INTO PANIER(idCompte,idArticle) VALUES (1,3);

INSERT INTO NOTE(idVendeur,idAcheteur,note) VALUES (1,2,4);

INSERT INTO MESSAGE(idExpediteur, idDestinataire, contenu, dateEnvoi) VALUES (1,2,'t trop beau tomtom <3','2019-01-07 18:57:18');
INSERT INTO MESSAGE(idExpediteur, idDestinataire, contenu, dateEnvoi) VALUES (1,2,'et t fort','2019-01-07 18:57:19');
INSERT INTO MESSAGE(idExpediteur, idDestinataire, contenu, dateEnvoi) VALUES (2,1,'je c','2019-01-07 18:57:20');
INSERT INTO MESSAGE(idExpediteur, idDestinataire, contenu, dateEnvoi) VALUES (2,1,'plus que vous tous réuni','2019-01-07 18:57:21');
INSERT INTO MESSAGE(idExpediteur, idDestinataire, contenu, dateEnvoi) VALUES (2,1,'t as un stage ?','2019-01-07 18:57:22');
INSERT INTO MESSAGE(idExpediteur, idDestinataire, contenu, dateEnvoi) VALUES (1,2,'Non','2019-01-07 18:57:23');
INSERT INTO MESSAGE(idExpediteur, idDestinataire, contenu, dateEnvoi) VALUES (1,2,'toi','2019-01-07 18:57:24');
INSERT INTO MESSAGE(idExpediteur, idDestinataire, contenu, dateEnvoi) VALUES (2,1,'la honte','2019-01-07 18:57:25');
INSERT INTO MESSAGE(idExpediteur, idDestinataire, contenu, dateEnvoi) VALUES (2,1,'moi non plus','2019-01-07 18:57:26');
INSERT INTO MESSAGE(idExpediteur, idDestinataire, contenu, dateEnvoi) VALUES (1,2,'?','2019-01-07 18:57:27');
INSERT INTO MESSAGE(idExpediteur, idDestinataire, contenu, dateEnvoi) VALUES (1,2,'Mdr','2019-01-07 18:57:28');
INSERT INTO MESSAGE(idExpediteur, idDestinataire, contenu, dateEnvoi) VALUES (1,2,'T naz','2019-01-07 18:57:29');
INSERT INTO MESSAGE(idExpediteur, idDestinataire, contenu, dateEnvoi) VALUES (1,2,'Une fois de plus','2019-01-07 18:57:30');
INSERT INTO MESSAGE(idExpediteur, idDestinataire, contenu, dateEnvoi) VALUES (2,1,'Supercell tu veux dire','2019-01-07 18:57:31');
INSERT INTO MESSAGE(idExpediteur, idDestinataire, contenu, dateEnvoi) VALUES (1,2,'oué','2019-01-07 18:57:32');
