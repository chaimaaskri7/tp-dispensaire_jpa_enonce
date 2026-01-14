package pharmacie.entity;

import jakarta.persistence.*;

import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    Integer Numero;

    LocalDate SaisieLeDate;

    LocalDate Envoyeele;

    @PositiveOrZero
    @Column(precision = 18, scale = 2)
    BigDecimal Port;

    @PositiveOrZero
    @Column(precision = 10, scale = 2)
    BigDecimal Remise;

    @ManyToOne(optional = false)
    @JoinColumn(name = "Dispensaire_Code")
    @ToString.Exclude
    private Dispensaire dispensaire;

    @Embedded
    @ToString.Exclude
    private AdressePostale adresseLivraison;

    @Size(max = 60)
    @Column(length = 60)
    String Destinataire;

    @ToString.Exclude
    @OneToMany(cascade = { CascadeType.ALL }, mappedBy = "commande")
    private List<Ligne> lignes = new LinkedList<>();

}
