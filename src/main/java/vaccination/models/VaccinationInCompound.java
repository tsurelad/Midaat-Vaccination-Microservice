package vaccination.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Vaccination in Compound
 */
@Entity
@Data
public class VaccinationInCompound implements IdObject {
    @Id
    String id;

    @ManyToOne
    @JoinColumn(name = "vaccination_id")
    Vaccination vaccination;

    @ManyToOne
    @JoinColumn(name = "compound_id")
    Compound compound;

    String description;
}
