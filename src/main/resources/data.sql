-- Données de base pour le projet Pharmacie
-- Dispensaire (Etablissements de santé qui passent commande de médicaments)
-- Le fichier est chargé au démarrage de l''application

-- Insertion des catégories de médicaments
INSERT INTO CATEGORIE (CODE, LIBELLE, DESCRIPTION) VALUES
(DEFAULT, 'Antalgiques et Antipyrétiques', 'Médicaments contre la douleur et la fièvre'), -- code : 1
(DEFAULT, 'Anti-inflammatoires', 'Médicaments réduisant l''inflammation'), -- code : 2
(DEFAULT, 'Antibiotiques', 'Médicaments pour traiter les infections bactériennes'),
(DEFAULT, 'Antihypertenseurs', 'Médicaments pour traiter l''hypertension artérielle'),
(DEFAULT, 'Antidiabétiques', 'Médicaments pour traiter le diabète'),
(DEFAULT, 'Antihistaminiques', 'Médicaments pour traiter les allergies'),
(DEFAULT, 'Vitamines et Compléments', 'Suppléments nutritionnels'),
(DEFAULT, 'Médicaments Cardiovasculaires', 'Médicaments pour le cœur et la circulation'),
(DEFAULT, 'Médicaments Gastro-intestinaux', 'Médicaments pour les troubles digestifs'),
(DEFAULT, 'Médicaments Respiratoires', 'Médicaments pour les troubles respiratoires');


-- Catégorie 1: Antalgiques et Antipyrétiques
INSERT INTO MEDICAMENT (NOM, CATEGORIE_CODE, QUANTITE_PAR_UNITE, PRIX_UNITAIRE, UNITES_EN_STOCK, UNITES_COMMANDEES, NIVEAU_DE_REAPPRO, INDISPONIBLE, imageURL) VALUES
('Morphine 10mg', 1, 'Boîte de 14 comprimés', 25.80, 80, 0, 15, false, 'https://images.unsplash.com/photo-1550572017-edd951aa8f72?w=400'),
('Doliprane Effervescent 1g', 1, 'Boîte de 8 comprimés', 3.50, 280, 0, 30, false, 'https://images.unsplash.com/photo-1587854692152-cbe660dbde88?w=400'),
('Efferalgan Vitamine C', 1, 'Boîte de 16 comprimés', 4.20, 220, 0, 25, false, 'https://images.unsplash.com/photo-1576091160550-2173dba999ef?w=400');

-- Catégorie 2: Anti-inflammatoires
INSERT INTO MEDICAMENT (NOM, CATEGORIE_CODE, QUANTITE_PAR_UNITE, PRIX_UNITAIRE, UNITES_EN_STOCK, UNITES_COMMANDEES, NIVEAU_DE_REAPPRO, INDISPONIBLE, imageURL) VALUES
('Étodolac 400mg', 2, 'Boîte de 14 comprimés', 12.50, 110, 0, 15, false, 'https://images.unsplash.com/photo-1471864190281-a93a3070b6de?w=400'),
('Flurbiprofène 100mg', 2, 'Boîte de 30 comprimés', 10.80, 130, 0, 16, false, 'https://images.unsplash.com/photo-1550572017-edd951aa8f72?w=400');

-- Catégorie 3: Antibiotiques (2 médicaments indisponbibles)
INSERT INTO MEDICAMENT (NOM, CATEGORIE_CODE, QUANTITE_PAR_UNITE, PRIX_UNITAIRE, UNITES_EN_STOCK, UNITES_COMMANDEES, NIVEAU_DE_REAPPRO, INDISPONIBLE, imageURL) VALUES
('Lévofloxacine 500mg', 3, 'Boîte de 7 comprimés', 15.80, 160, 0, 18, true, 'https://images.unsplash.com/photo-1628771065518-0d82f1938462?w=400'),
('Clindamycine 300mg', 3, 'Boîte de 16 gélules', 13.20, 140, 0, 16, true, 'https://images.unsplash.com/photo-1584308666744-24d5c474f2ae?w=400');
-- Insertion des dispensaires (Établissements de santé)
INSERT INTO DISPENSAIRE (CODE, NOM, FONCTION, CONTACT, TELEPHONE, FAX, ADRESSE, VILLE, REGION, CODE_POSTAL, PAYS) VALUES
(DEFAULT, 'Hôpital Central', 'Pharmacien', 'Dr. Martin', '01-45-67-89', '01-45-67-90', '123 Rue de la Paix', 'Paris', 'Île-de-France', '75001', 'France'),
(DEFAULT, 'Clinique Saint-Vincent', 'Pharmacienne', 'Mlle Dupont', '02-96-34-12', '02-96-34-13', '456 Avenue Pasteur', 'Lyon', 'Rhône-Alpes', '69000', 'France'),
(DEFAULT, 'Hôpital de Marseille', 'Chef Pharmacie', 'Mr. Bernard', '04-91-22-33', '04-91-22-34', '789 Boulevard Longchamp', 'Marseille', 'Provence-Alpes-Côte d''Azur', '13000', 'France'),
(DEFAULT, 'Clinique du Littoral', 'Pharmacien', 'Mr. Leclerc', '03-20-45-67', '03-20-45-68', '321 Chemin des Dunes', 'Boulogne-sur-Mer', 'Nord-Pas-de-Calais', '62200', 'France'),
(DEFAULT, 'Centre Médical Toulouse', 'Pharmacienne', 'Mlle Garnier', '05-61-80-90', '05-61-80-91', '654 Rue de la République', 'Toulouse', 'Midi-Pyrénées', '31000', 'France');

-- Insertion des commandes
INSERT INTO COMMANDE (NUMERO, SAISIE_LE_DATE, ENVOYEELE, PORT, REMISE, DISPENSAIRE_CODE, ADRESSE, VILLE, REGION, CODE_POSTAL, PAYS, DESTINATAIRE) VALUES
(DEFAULT, CAST('2025-01-10' AS DATE), NULL, 15.50, 2.50, 1, '789 Boulevard Saint-Germain', 'Paris', 'Île-de-France', '75005', 'France', 'Pharmacie Centrale'),
(DEFAULT, CAST('2025-01-12' AS DATE), CAST('2025-01-15' AS DATE), 20.00, 5.00, 2, '999 Rue de la Paix', 'Lyon', 'Rhône-Alpes', '69001', 'France', 'Pharmacie Lyonnaise'),
(DEFAULT, CAST('2025-01-14' AS DATE), NULL, 25.00, 0.00, 3, '444 Avenue des Roses', 'Marseille', 'Provence-Alpes-Côte d''Azur', '13001', 'France', 'Pharmacie Sud'),
(DEFAULT, CAST('2025-01-16' AS DATE), CAST('2025-01-18' AS DATE), 10.00, 1.50, 4, '567 Rue du Marché', 'Boulogne-sur-Mer', 'Nord-Pas-de-Calais', '62201', 'France', 'Pharmacie Nord'),
(DEFAULT, CAST('2025-01-18' AS DATE), NULL, 30.00, 3.00, 5, '888 Place Toulouse', 'Toulouse', 'Midi-Pyrénées', '31001', 'France', 'Pharmacie Sud-Ouest');

-- Insertion des lignes de commande (détail des articles)
INSERT INTO LIGNE (ID, QUANTITE, COMMANDE_NUMERO, MEDICAMENT_REFERENCE) VALUES
(DEFAULT, 2, 1, 1),  -- 2x Morphine 10mg pour commande 1
(DEFAULT, 5, 1, 2),  -- 5x Doliprane Effervescent 1g pour commande 1
(DEFAULT, 3, 2, 3),  -- 3x Efferalgan Vitamine C pour commande 2
(DEFAULT, 2, 2, 4),  -- 2x Étodolac 400mg pour commande 2
(DEFAULT, 4, 3, 2),  -- 4x Doliprane Effervescent 1g pour commande 3
(DEFAULT, 1, 3, 5),  -- 1x Flurbiprofène 100mg pour commande 3
(DEFAULT, 6, 4, 1),  -- 6x Morphine 10mg pour commande 4
(DEFAULT, 3, 4, 4),  -- 3x Étodolac 400mg pour commande 4
(DEFAULT, 2, 5, 3),  -- 2x Efferalgan Vitamine C pour commande 5
(DEFAULT, 5, 5, 5);  -- 5x Flurbiprofène 100mg pour commande 5