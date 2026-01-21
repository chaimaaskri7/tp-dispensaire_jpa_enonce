package pharmacie.dto;

/**
 * DTO pour récupérer les résultats des requêtes qui retournent
 * le nom du médicament et la somme des quantités commandées
 * 
 * Cette classe est utilisée pour les projections de résultats
 * via des requêtes JPQL et SQL natives avec GROUP BY
 */
public interface UnitsParMedicament {
    /**
     * Récupère le nom du médicament
     * 
     * @return le nom du médicament
     */
    String getNom();

    /**
     * Récupère la somme des unités commandées pour ce médicament
     * 
     * @return le nombre total d'unités
     */
    Long getUnites();
}
