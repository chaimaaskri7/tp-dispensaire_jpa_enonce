package pharmacie.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class Ligne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    Integer Id;

    Integer Quantite;

    @ManyToOne(optional = false)
    @JoinColumn(name = "Commande_Numero")
    @ToString.Exclude
    private Commande commande;

    @ManyToOne(optional = false)
    @JoinColumn(name = "Medicament_Reference")
    @ToString.Exclude
    private Medicament medicament;
}
