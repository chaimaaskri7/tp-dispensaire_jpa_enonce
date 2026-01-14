package pharmacie.entity;

import jakarta.validation.constraints.PositiveOrZero;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Embedded;
import jakarta.persistence.CascadeType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.LinkedList;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class Dispensaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    Integer Code;
    @Size(max = 24)
    @Column(length = 24)
    String Fax;
    @Size(max = 24)
    @Column(length = 24)
    String Telephone;
    @Size(max = 30)
    @Column(length = 30)
    String Contact;
    @Size(max = 30)
    @Column(length = 30)
    String Fonction;
    @Size(max = 40)
    @Column(length = 40)
    String Nom;

    @Embedded
    @ToString.Exclude
    private AdressePostale adresse;

    @ToString.Exclude
    @OneToMany(cascade = { CascadeType.ALL }, mappedBy = "dispensaire")
    private List<Commande> commandes = new LinkedList<>();

}
