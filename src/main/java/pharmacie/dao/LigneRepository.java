package pharmacie.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pharmacie.entity.Ligne;

/**
 * Repository pour gérer les Ligne (articles des commandes)
 * Auto-implémenté par Spring
 */
public interface LigneRepository extends JpaRepository<Ligne, Integer> {
    /**
     * Recherche les lignes d'une commande
     * 
     * @param commandeNumero le numéro de la commande
     * @return Une liste de lignes
     */
    @Query("SELECT l FROM Ligne l WHERE l.commande.Numero = :commandeNumero")
    List<Ligne> findByCommandeNumero(@Param("commandeNumero") Integer commandeNumero);

    /**
     * Recherche les lignes contenant un medicament
     * 
     * @param medicamentReference la référence du médicament
     * @return Une liste de lignes
     */
    List<Ligne> findByMedicamentReference(Integer medicamentReference);

    /**
     * Recherche les lignes d'une commande pour un médicament
     * 
     * @param commandeNumero      le numéro de la commande
     * @param medicamentReference la référence du médicament
     * @return Une liste de lignes
     */
    @Query("SELECT l FROM Ligne l WHERE l.commande.Numero = :commandeNumero AND l.medicament.reference = :medicamentReference")
    List<Ligne> findByCommandeNumeroAndMedicamentReference(@Param("commandeNumero") Integer commandeNumero,
            @Param("medicamentReference") Integer medicamentReference);
}
