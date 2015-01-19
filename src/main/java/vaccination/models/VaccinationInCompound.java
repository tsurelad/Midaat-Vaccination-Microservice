package vaccination.models;

import lombok.Data;

import javax.persistence.*;

/**
 * Vaccination in Compound
 */
@Entity
@Data
public class VaccinationInCompound implements IdObject {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    Long id;

    @ManyToOne
    @JoinColumn(name = "vaccination_id")
    Vaccination vaccination;

    @ManyToOne
    @JoinColumn(name = "compound_id")
    Compound compound;

    String description;
}
