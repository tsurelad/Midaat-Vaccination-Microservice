package vaccination.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Compound in Vaccination Date
 */
@Entity
@Data
public class CompoundInVaccinationDate implements IdObject{
    @Id
    String id;

    @ManyToOne
    @JoinColumn(name = "compound_id")
    Compound compound;

    @ManyToOne
    @JoinColumn(name = "vaccinationdate_id")
    VaccinationDate vaccinationDate;

    String description;
}
