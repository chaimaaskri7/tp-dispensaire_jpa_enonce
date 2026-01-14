package pharmacie.dao;

import java.util.Optional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pharmacie.entity.Dispensaire;

/**
 * Repository pour gérer les Dispensaire
 * Auto-implémenté par Spring
 */
public interface DispensaireRepository extends JpaRepository<Dispensaire, Integer> {
    /**
     * Recherche un dispensaire par son nom
     * 
     * @param nom le nom du dispensaire
     * @return Un dispensaire optionnel
     */
    @Query("SELECT d FROM Dispensaire d WHERE d.Nom = :nom")
    Optional<Dispensaire> findByNom(@Param("nom") String nom);

    /**
     * Recherche les dispensaires d'un pays
     * 
     * @param pays le pays
     * @return Une liste de dispensaires
     */
    @Query("SELECT d FROM Dispensaire d WHERE d.adresse.Pays = :pays")
    List<Dispensaire> findByAdressePays(@Param("pays") String pays);

    /**
     * Recherche les dispensaires d'une ville
     * 
     * @param ville la ville
     * @return Une liste de dispensaires
     */
    @Query("SELECT d FROM Dispensaire d WHERE d.adresse.Ville = :ville")
    List<Dispensaire> findByAdresseVille(@Param("ville") String ville);

    /**
     * Recherche tous les dispensaires dans une région donnée
     * 
     * @param region la région
     * @return Une liste de dispensaires
     */
    @Query("SELECT d FROM Dispensaire d WHERE d.adresse.Region = :region")
    List<Dispensaire> findByAdresseRegion(@Param("region") String region);
}
