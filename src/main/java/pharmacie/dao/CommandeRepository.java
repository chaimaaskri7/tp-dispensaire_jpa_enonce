package pharmacie.dao;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pharmacie.entity.Commande;

/**
 * Repository pour gérer les Commande
 * Auto-implémenté par Spring
 */
public interface CommandeRepository extends JpaRepository<Commande, Integer> {
    /**
     * Recherche les commandes d'un dispensaire
     * 
     * @param dispensaireCode le code du dispensaire
     * @return Une liste de commandes
     */
    @Query("SELECT c FROM Commande c WHERE c.dispensaire.Code = :dispensaireCode")
    List<Commande> findByDispensaireCode(@Param("dispensaireCode") Integer dispensaireCode);

    /**
     * Recherche les commandes envoyées entre deux dates
     * 
     * @param dateDebut la date de début
     * @param dateFin   la date de fin
     * @return Une liste de commandes
     */
    @Query("SELECT c FROM Commande c WHERE c.Envoyeele BETWEEN :dateDebut AND :dateFin")
    List<Commande> findByEnvoyeeleBetween(@Param("dateDebut") LocalDate dateDebut, @Param("dateFin") LocalDate dateFin);

    /**
     * Recherche les commandes non expédiées (Envoyeele = null)
     * 
     * @return Une liste de commandes
     */
    @Query("SELECT c FROM Commande c WHERE c.Envoyeele IS NULL")
    List<Commande> findByEnvoyeeleIsNull();

    /**
     * Recherche toutes les commandes saisies après une date donnée
     * 
     * @param date la date de référence
     * @return Une liste de commandes saisies après cette date
     */
    @Query("SELECT c FROM Commande c WHERE c.SaisieLeDate > :date")
    List<Commande> findBySaisieLeeDateAfter(@Param("date") java.time.LocalDate date);
}
