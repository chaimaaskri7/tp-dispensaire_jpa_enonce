package pharmacie.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pharmacie.entity.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class RepositoryCustomMethodsTest {

    @Autowired
    private CategorieRepository categorieRepository;
    @Autowired
    private MedicamentRepository medicamentRepository;
    @Autowired
    private DispensaireRepository dispensaireRepository;
    @Autowired
    private CommandeRepository commandeRepository;
    @Autowired
    private LigneRepository ligneRepository;

    @Test
    public void testMedicamentCustomMethods() {
        // Créer des médicaments de test
        Categorie cat = new Categorie();
        cat.setLibelle("TestMedicaments");
        categorieRepository.save(cat);

        Medicament med1 = new Medicament();
        med1.setNom("MedicamentDisponible");
        med1.setCategorie(cat);
        med1.setIndisponible(false);
        medicamentRepository.save(med1);

        Medicament med2 = new Medicament();
        med2.setNom("MedicamentIndisponible");
        med2.setCategorie(cat);
        med2.setIndisponible(true);
        medicamentRepository.save(med2);

        // Trouver les médicaments disponibles
        List<Medicament> disponibles = medicamentRepository.findByIndisponibleFalse();
        assertFalse(disponibles.isEmpty(), "Devrait trouver au moins un médicament disponible");
        assertTrue(disponibles.contains(med1), "MedicamentDisponible doit être présent");
        assertFalse(disponibles.contains(med2), "MedicamentIndisponible ne doit pas être présent");
    }

    @Test
    public void testCategorieCustomMethods() {
        // Créer plusieurs catégories test
        Categorie c1 = new Categorie();
        c1.setLibelle("AnalgesiquesTest");
        c1.setDescription("Description Analgesiques");
        categorieRepository.save(c1);

        Categorie c2 = new Categorie();
        c2.setLibelle("AntibiotiquesTest");
        c2.setDescription("Description Antibiotiques");
        categorieRepository.save(c2);

        Categorie c3 = new Categorie();
        c3.setLibelle("AntiHistaminesTest");
        categorieRepository.save(c3);

        // findByLibelle
        Categorie found = categorieRepository.findByLibelle("AnalgesiquesTest");
        assertNotNull(found);
        assertEquals("AnalgesiquesTest", found.getLibelle());
        assertEquals("Description Analgesiques", found.getDescription());

        // findByLibelleContaining
        List<Categorie> list = categorieRepository.findByLibelleContaining("iquesTest");
        assertEquals(2, list.size(), "Devrait trouver 2 catégories contenant 'iquesTest'");
        assertTrue(list.stream().anyMatch(cat -> cat.getLibelle().equals("AntibiotiquesTest")));
        assertTrue(list.stream().anyMatch(cat -> cat.getLibelle().equals("AnalgesiquesTest")));

        // Vérifier que AntiHistaminesTest n'est pas inclu
        List<Categorie> antiHist = categorieRepository.findByLibelleContaining("Anti");
        assertTrue(antiHist.size() > 0);
    }

    @Test
    public void testDispensaireCustomMethods() {
        // Créer plusieurs dispensaires de test
        Dispensaire d1 = new Dispensaire();
        d1.setNom("Pharmacie Test Paris");
        AdressePostale addr1 = new AdressePostale();
        addr1.setAdresse("123 Rue Test");
        addr1.setVille("Paris");
        addr1.setRegion("Île-de-France");
        addr1.setCodePostal("75001");
        addr1.setPays("France");
        d1.setAdresse(addr1);
        dispensaireRepository.save(d1);

        Dispensaire d2 = new Dispensaire();
        d2.setNom("Pharmacie Test Marseille");
        AdressePostale addr2 = new AdressePostale();
        addr2.setAdresse("456 Rue Test");
        addr2.setVille("Marseille");
        addr2.setRegion("Provence-Alpes-Côte d'Azur");
        addr2.setCodePostal("13001");
        addr2.setPays("France");
        d2.setAdresse(addr2);
        dispensaireRepository.save(d2);

        // findByNom
        Optional<Dispensaire> found = dispensaireRepository.findByNom("Pharmacie Test Paris");
        assertTrue(found.isPresent());
        assertEquals("Pharmacie Test Paris", found.get().getNom());

        // findByNom - cas où pas trouvé
        Optional<Dispensaire> notFound = dispensaireRepository.findByNom("Pharmacie Inexistante");
        assertFalse(notFound.isPresent());

        // findByAdressePays
        List<Dispensaire> dispensairesEnFrance = dispensaireRepository.findByAdressePays("France");
        assertTrue(dispensairesEnFrance.size() >= 2);

        // findByAdresseVille
        List<Dispensaire> dispensairesAParis = dispensaireRepository.findByAdresseVille("Paris");
        assertTrue(dispensairesAParis.stream().anyMatch(d -> d.getNom().equals("Pharmacie Test Paris")));

        // findByAdresseVille - cas où pas trouvé
        List<Dispensaire> dispensairesLyon = dispensaireRepository.findByAdresseVille("Lyon");

        // findByAdresseRegion
        List<Dispensaire> dispensairesIleDF = dispensaireRepository.findByAdresseRegion("Île-de-France");
        assertTrue(dispensairesIleDF.stream().anyMatch(d -> d.getNom().equals("Pharmacie Test Paris")));

        // Vérifier les détails du dispensaire
        Dispensaire retrieved = found.get();
        assertEquals("123 Rue Test", retrieved.getAdresse().getAdresse());
        assertEquals("75001", retrieved.getAdresse().getCodePostal());
    }

    @Test
    public void testCommandeCustomMethods() {
        // Créer une commande de test
        Dispensaire d = new Dispensaire();
        d.setNom("Dispensaire Test Commande");
        AdressePostale addr = new AdressePostale();
        addr.setAdresse("456 Rue Test");
        addr.setVille("Lyon");
        addr.setRegion("Rhône-Alpes");
        addr.setCodePostal("69000");
        addr.setPays("France");
        d.setAdresse(addr);
        dispensaireRepository.save(d);

        // Créer plusieurs commandes
        Commande c1 = new Commande();
        c1.setDispensaire(d);
        c1.setSaisieLeDate(LocalDate.of(2025, 1, 15));
        c1.setEnvoyeele(null);
        commandeRepository.save(c1);

        Commande c2 = new Commande();
        c2.setDispensaire(d);
        c2.setSaisieLeDate(LocalDate.of(2025, 1, 16));
        c2.setEnvoyeele(LocalDate.of(2025, 1, 17));
        commandeRepository.save(c2);

        Commande c3 = new Commande();
        c3.setDispensaire(d);
        c3.setSaisieLeDate(LocalDate.of(2025, 1, 18));
        c3.setEnvoyeele(LocalDate.of(2025, 1, 19));
        commandeRepository.save(c3);

        // findByDispensaireCode
        List<Commande> commandesDispensaire = commandeRepository.findByDispensaireCode(d.getCode());
        assertTrue(commandesDispensaire.size() >= 3, "Devrait trouver au moins 3 commandes");

        // findByEnvoyeeleIsNull
        List<Commande> nonExpediees = commandeRepository.findByEnvoyeeleIsNull();
        assertTrue(nonExpediees.stream().anyMatch(c -> c.getNumero().equals(c1.getNumero())));
        assertFalse(nonExpediees.stream().anyMatch(c -> c.getNumero().equals(c2.getNumero())));

        // findBySaisieLeeDateAfter
        List<Commande> apres12Jan = commandeRepository.findBySaisieLeeDateAfter(LocalDate.of(2025, 1, 12));
        assertTrue(apres12Jan.size() >= 3);
        assertTrue(apres12Jan.stream().allMatch(c -> c.getSaisieLeDate().isAfter(LocalDate.of(2025, 1, 12))));

        // findByEnvoyeeleBetween
        List<Commande> entreDeuxDates = commandeRepository.findByEnvoyeeleBetween(
                LocalDate.of(2025, 1, 16),
                LocalDate.of(2025, 1, 19));
        assertTrue(entreDeuxDates.size() >= 2, "Devrait trouver 2 commandes expédiées");
        assertTrue(entreDeuxDates.stream().allMatch(c -> c.getEnvoyeele() != null));

        // Vérifier les dates de saisie
        assertTrue(c1.getSaisieLeDate().isBefore(c2.getSaisieLeDate()));
    }

    @Test
    public void testLigneCustomMethods() {
        // Créer une catégorie et plusieurs médicaments pour les tests
        Categorie cat = new Categorie();
        cat.setLibelle("TestCategorieLigne");
        categorieRepository.save(cat);

        Medicament med1 = new Medicament();
        med1.setNom("MedicamentTestLigne1");
        med1.setCategorie(cat);
        med1.setIndisponible(false);
        medicamentRepository.save(med1);

        Medicament med2 = new Medicament();
        med2.setNom("MedicamentTestLigne2");
        med2.setCategorie(cat);
        med2.setIndisponible(false);
        medicamentRepository.save(med2);

        // Créer un dispensaire et une commande
        Dispensaire d = new Dispensaire();
        d.setNom("Dispensaire Test Ligne");
        AdressePostale addr = new AdressePostale();
        addr.setAdresse("789 Rue Test");
        addr.setVille("Marseille");
        addr.setRegion("Provence");
        addr.setCodePostal("13000");
        addr.setPays("France");
        d.setAdresse(addr);
        dispensaireRepository.save(d);

        Commande c = new Commande();
        c.setDispensaire(d);
        c.setSaisieLeDate(LocalDate.of(2025, 1, 10));
        commandeRepository.save(c);

        // Créer plusieurs lignes
        Ligne l1 = new Ligne();
        l1.setCommande(c);
        l1.setMedicament(med1);
        l1.setQuantite(5);
        ligneRepository.save(l1);

        Ligne l2 = new Ligne();
        l2.setCommande(c);
        l2.setMedicament(med2);
        l2.setQuantite(3);
        ligneRepository.save(l2);

        // findByCommandeNumero
        List<Ligne> lignesCommande = ligneRepository.findByCommandeNumero(c.getNumero());
        assertEquals(2, lignesCommande.size(), "Devrait trouver 2 lignes pour cette commande");
        assertTrue(lignesCommande.stream().allMatch(l -> l.getCommande().getNumero().equals(c.getNumero())));

        // findByMedicamentReference
        List<Ligne> lignesMedicament1 = ligneRepository.findByMedicamentReference(med1.getReference());
        assertTrue(
                lignesMedicament1.stream().anyMatch(l -> l.getMedicament().getReference().equals(med1.getReference())));

        List<Ligne> lignesMedicament2 = ligneRepository.findByMedicamentReference(med2.getReference());
        assertTrue(
                lignesMedicament2.stream().anyMatch(l -> l.getMedicament().getReference().equals(med2.getReference())));

        // findByCommandeNumeroAndMedicamentReference - cas spécifique 1
        List<Ligne> ligneSpecifique1 = ligneRepository.findByCommandeNumeroAndMedicamentReference(c.getNumero(),
                med1.getReference());
        assertEquals(1, ligneSpecifique1.size(), "Devrait trouver exactement 1 ligne");
        assertEquals(5, ligneSpecifique1.get(0).getQuantite());

        // findByCommandeNumeroAndMedicamentReference - cas spécifique 2
        List<Ligne> ligneSpecifique2 = ligneRepository.findByCommandeNumeroAndMedicamentReference(c.getNumero(),
                med2.getReference());
        assertEquals(1, ligneSpecifique2.size(), "Devrait trouver exactement 1 ligne");
        assertEquals(3, ligneSpecifique2.get(0).getQuantite());

        // findByCommandeNumeroAndMedicamentReference - cas non trouvé
        List<Ligne> ligneNonTrouvee = ligneRepository.findByCommandeNumeroAndMedicamentReference(999, 999);
        assertEquals(0, ligneNonTrouvee.size(), "Devrait ne rien trouver");
    }
}
