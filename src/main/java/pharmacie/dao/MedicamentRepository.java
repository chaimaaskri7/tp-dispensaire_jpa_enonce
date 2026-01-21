package pharmacie.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pharmacie.dto.UnitsParMedicament;
import pharmacie.entity.Medicament;

// Cette interface sera auto-implémentée par Spring
public interface MedicamentRepository extends JpaRepository<Medicament, Integer> {
    /**
     * Trouve un médicament à partir de son nom (unique dans Medicament)
     * 
     * @return un médicament "optionnel"
     */
    Optional<Medicament> findByNom(String nom);

    /**
     * Trouve les médicaments disponibles (indisponible = false)
     * 
     * @return la liste des médicaments disponibles
     */
    List<Medicament> findByIndisponibleFalse();

    /**
     * Trouve tous les médicaments disponibles à la commande pour une catégorie
     * donnée
     * Un médicament est disponible à la commande si :
     * - Il n'est pas indisponible (indisponible = false)
     * - Sa quantité en stock (unitesEnStock) >= sa quantité en commande
     * (unitesCommandees)
     * 
     * @param categorieCode le code de la catégorie
     * @return la liste des médicaments disponibles à la commande pour cette
     *         catégorie
     */
    @Query("SELECT m FROM Medicament m " +
            "WHERE m.categorie.code = :categorieCode " +
            "AND m.indisponible = false " +
            "AND m.unitesEnStock >= m.unitesCommandees")
    List<Medicament> findMedicamentsAvailableForOrderByCategorie(@Param("categorieCode") Integer categorieCode);

    /**
     * Calcule le nombre d'unités commandées pour chaque produit d'une catégorie
     * Requête JPQL (Diapositive 51 du support de cours)
     * 
     * @param codeCategorie la catégorie à traiter
     * @return une liste de projections contenant le nom du médicament et la somme
     *         des unités
     */
    @Query("SELECT l.medicament.nom as nom, SUM(l.Quantite) AS unites " +
            "FROM Ligne l " +
            "WHERE l.medicament.categorie.code = :codeCategorie " +
            "GROUP BY l.medicament.nom")
    List<UnitsParMedicament> medicamentsCommandesPour(@Param("codeCategorie") Integer codeCategorie);

    /**
     * Calcule le nombre d'unités commandées pour chaque produit d'une catégorie
     * Requête SQL native (Diapositive 51 du support de cours)
     * 
     * @param codeCategorie la catégorie à traiter
     * @return une liste de projections contenant le nom du médicament et la somme
     *         des unités
     */
    @Query(value = "SELECT m.nom as nom, SUM(l.Quantite) AS unites " +
            "FROM Categorie c " +
            "INNER JOIN Medicament m ON c.code = m.categorie_code " +
            "INNER JOIN Ligne l ON m.reference = l.medicament_reference " +
            "WHERE c.code = :codeCategorie " +
            "GROUP BY m.nom", nativeQuery = true)
    List<UnitsParMedicament> medicamentsCommandesPourNative(@Param("codeCategorie") Integer codeCategorie);
}
