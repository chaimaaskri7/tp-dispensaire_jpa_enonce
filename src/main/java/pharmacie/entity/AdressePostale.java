package pharmacie.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AdressePostale {
    @Size(max = 60)
    @Column(length = 60)
    @NotBlank
    String Adresse;

    @Size(max = 25)
    @Column(length = 25)
    @NotBlank
    String Ville;

    @Size(max = 30)
    @Column(length = 30)
    String Region;

    @Size(max = 10)
    @Column(length = 10)
    @NotBlank
    String CodePostal;

    @Size(max = 15)
    @Column(length = 15)
    @NotBlank
    String Pays;
}
